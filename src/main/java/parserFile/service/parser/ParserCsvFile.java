package parserFile.service.parser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import parserFile.dto.PassportDataDto;
import parserFile.service.PassportDataService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * TODO: Мне не нравится что вызов дао-слоя происходит из парсера.
 * Ответсвенность парсера - принимать на вход данные одного типа, и возврашать данные целевого типа.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParserCsvFile implements FileParser {
    private final PassportDataService fileService;

    //TODO: CsvToBean - переделать
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





