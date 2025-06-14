package ru.byrmistrov.fileParser.service.parser;

import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.HasPassportNumber;
import ru.byrmistrov.fileParser.dto.PassportDataDtoPojo;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service("xlsx")
@RequiredArgsConstructor
public class ParserXlsxFile implements FileParser {
    private final ValidatorsXlsx passportDataValidatorsXlsx;


    @Override
    public List<? extends HasPassportNumber> parseFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {

            return EasyExcel.read(inputStream)
                    .head(PassportDataDtoPojo.class)
                    .sheet()
                    .registerReadListener(passportDataValidatorsXlsx)
                    .doReadSync();


        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }
}



