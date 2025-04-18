package parserFile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@Service
@RequiredArgsConstructor
public class WatcherService {

    private final XlsxFileScanner checkingTheXlsxFile;
    private final CsvFileScanner checkingTheCsvFile;

    public void watchInDirectory(Path folderPath) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();


            folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path fileName = (Path) event.context();
                        Path fullPath = folderPath.resolve(fileName);
                        checkingTheXlsxFile.scanFolderForFiles(fullPath);
                        checkingTheCsvFile.scanFolderForFiles(fullPath);
                    }
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            System.err.println("Ошибка мониторинга директории " + e.getMessage());
        }
    }
}






