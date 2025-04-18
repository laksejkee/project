package parserFile.service.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import parserFile.mapper.XlsxDataMapperToString;
import parserFile.service.PassportDataService;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;


/**
 * TODO: Мне не нравится что вызов дао-слоя происходит из парсера.
 * Ответсвенность парсера - принимать на вход данные одного типа, и возврашать данные целевого типа.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ParserXlsxFile implements FileParser {
    private final PassportDataService fileService;
    private final XlsxDataMapperToString xlsxDataMapperToString;
    //TODO: Запилить умную валидация hibernate.validation.api
    public void parseFile(Path path) {
        try (FileInputStream fis = new FileInputStream(path.toString())) {
            Workbook workbook = WorkbookFactory.create(fis);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        log.warn("есть пустая строка в {}", sheet.getSheetName() );
                        continue;
                    }
                    try {
                        String[] apply = xlsxDataMapperToString.apply(row);
                        fileService.saveToDatabase(apply);
                    } catch (Exception e) {
                        log.error("Ошибка в строке {} {} : {}", j, sheet.getSheetName(), e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            log.error("Ошибка чтения Xlsx файла {}", e.getMessage());
        }
    }
}
