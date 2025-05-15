package ru.byrmistrov.fileParser.servicejob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.Mark;
import ru.byrmistrov.fileParser.service.FileScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class FilesScheduler {
    private final FileScanner fileScanner;
    private final NumberCache numberCache;

    @Value("${file.path}")
    private String path;

    @Scheduled(fixedRate = 20000, initialDelay = 5000)
    private void checkFiles() {
        Path folderPath = Path.of(path);
        Set<String> allPassportNumbers = numberCache.getAllPassportNumbers();
        try (Stream<Path> files = Files.list(folderPath)) {
            files.forEach(file -> {
                try {
                    boolean shouldDelete = fileScanner.parseFile(file)
                            .stream()
                            .map(Mark::getPassportNumber)
                            .anyMatch(allPassportNumbers::contains);

                    if (shouldDelete) {
                        Files.delete(file);
                    } else {
                        log.warn("файл: {} был найден в директории , но не записан в БД и не удален", file.getFileName());
                    }

                } catch (Exception e) {
                    log.warn("Ошибка обработка файла {} ", file.getFileName());
                }
            });
        } catch (IOException e) {
            log.error("Ошибка при чтении списка файлов", e);
        }
    }
}
