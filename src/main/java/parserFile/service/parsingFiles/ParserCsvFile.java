package parserFile.service.parsingFiles;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import parserFile.service.PassportDataService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParserCsvFile implements Fileparser {
    private final PassportDataService fileService;

    @Override
    public void parseFile(Path path) {
        try (CSVReader csvReaderBuilder = new CSVReaderBuilder(Files.newBufferedReader(path))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .build())
                .withSkipLines(1)
                .build()) {
            String[] row;
            int lineNumber = 1;
            try {
                while ((row = csvReaderBuilder.readNext()) != null) {
                    lineNumber++;
                    try {
                        fileService.saveToDatabase(row);
                    } catch (Exception e) {
                        log.error("Ошибка в строке {}: {} ",
                                lineNumber, e.getMessage());
                    }
                }
            } catch (CsvValidationException e) {
                log.error("Ошибка валидации CSV файла {}", e.getMessage());
            }
        } catch (IOException e) {
            log.error("Ошибка чтения Csv файла {}", e.getMessage());
        }
    }
}



