package ParserFile.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
@Service
@RequiredArgsConstructor
public class DirectoryWatcherService {

    WatchService watchService;
    @Autowired
    CheckingTheFileService checkingTheFileService;

    public void watсhInDirectory(Path folderPath)  {
        try {
            watchService = FileSystems.getDefault().newWatchService();


            folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path fileName = (Path) event.context();
                        Path fullPath = folderPath.resolve(fileName);
                        checkingTheFileService.checkOneFileInDirectory(fullPath);
                    }
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e){
            System.err.println("Ошибка мониторинга директории" + e.getMessage());
        }
    }
}






