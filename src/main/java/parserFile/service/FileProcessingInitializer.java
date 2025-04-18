package parserFile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import parserFile.service.chekingDirectoryOnTheFile.CsvFileScanner;
import parserFile.service.chekingDirectoryOnTheFile.XlsxFileScanner;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class FileProcessingInitializer implements CommandLineRunner {
    @Value("${file.path}")
    private String path;

    private final XlsxFileScanner checkingTheFileService;
    private final CsvFileScanner checkingTheCsvFile;
    private final WatcherService directoryWatcherService;


    @Override
    public void run(String... args) throws Exception {
        Path folderPath = Paths.get(path);
        //запускает поиск файла
        checkingTheFileService.processFilesInFolder(folderPath);
        checkingTheCsvFile.processFilesInFolder(folderPath);

//        // поступил ли новый файл
        directoryWatcherService.watchInDirectory(folderPath);
    }
}
