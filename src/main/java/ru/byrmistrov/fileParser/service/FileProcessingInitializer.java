package ru.byrmistrov.fileParser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class FileProcessingInitializer implements CommandLineRunner {
    @Value("${file.path}")
    private String path;
    private final PassportDataService passportDataService;
    private final WatcherService watcherService;

    @Override
    public void run(String... args) throws Exception {
        Path folderPath = Paths.get(path);

        //запускает поиск файла
        try (Stream<Path> files = Files.list(folderPath)) {
            files.forEach(passportDataService::saveToDatabase);
        }


//         запуск на запись приходящих файлов
        watcherService.watchInDirectory(folderPath);
    }
}
