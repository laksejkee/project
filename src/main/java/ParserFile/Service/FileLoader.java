package ParserFile.Service;

import ParserFile.Service.CheckingTheFileService;
import ParserFile.Service.DirectoryWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileLoader implements CommandLineRunner {
    @Value("${file.path}")
    private String path;

    @Autowired
    CheckingTheFileService checkingTheFileServis;
    @Autowired
    DirectoryWatcherService directoryWatcherService;


    @Override
    public void run(String... args) throws Exception {
        Path folderPath = Paths.get(path);
        //запускает поиск файла
        checkingTheFileServis.checkAllFilesInDirectory(folderPath);
        // поступил ли новый файл
        directoryWatcherService.watсhInDirectory(folderPath);
    }
}
