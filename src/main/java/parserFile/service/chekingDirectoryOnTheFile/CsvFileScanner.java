package parserFile.service.chekingDirectoryOnTheFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import parserFile.service.parsingFiles.ParserCsvFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsvFileScanner implements CheckTheFile {
    private final ParserCsvFile parserCsvFile;

    @Override
    public void processFilesInFolder(Path folderPath) {
        try (Stream<Path> list = Files.list(folderPath)) {
            list.filter(folderpath -> folderpath.getFileName().toString().endsWith(".csv"))
                    .forEach(parserCsvFile::parseFile);
        } catch (IOException e) {
            log.info("Ошибка при поиске файлов в директории {} ", e.getMessage());
        }
    }

    @Override
    public void scanFolderForFiles(Path path) {
        if (path.getFileName().toString().endsWith(".csv")) {
            parserCsvFile.parseFile(path);
        }
    }
}