package ru.byrmistrov.fileParser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

@Service
@RequiredArgsConstructor
public class WatcherService {

    private final PassportDataService passportDataService;

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
                        passportDataService.saveToDatabase(fullPath);
                    }
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            System.err.println("Ошибка мониторинга директории " + e.getMessage());
        }
    }
}






