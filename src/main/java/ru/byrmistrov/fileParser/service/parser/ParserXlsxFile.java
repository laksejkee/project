package ru.byrmistrov.fileParser.service.parser;

import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.Mark;
import ru.byrmistrov.fileParser.dto.PassportDataDtoPojo;
import ru.byrmistrov.fileParser.service.converter.IntegerConverterXlsx;
import ru.byrmistrov.fileParser.service.converter.LocalDateConverterXlsx;

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
    private final LocalDateConverterXlsx localDateConverter;
    private final IntegerConverterXlsx integerConverter;


    @Override
    public List<? extends Mark> parseFile(Path path) {
        try (InputStream inputStream = Files.newInputStream(path)) {

            return EasyExcel.read(inputStream)
                    .registerConverter(localDateConverter)
                    .registerConverter(integerConverter)
                    .head(PassportDataDtoPojo.class)
                    .sheet()
                    .registerReadListener(passportDataValidatorsXlsx)
                    .doReadSync();


        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }
}



