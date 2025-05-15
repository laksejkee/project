package ru.byrmistrov.fileParser.service.parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.PassportDataDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;


@Service(value = "csv")
@Slf4j
public class ParserCsvFile implements FileParser {

    @Override
    public List<PassportDataDto> parseFile(Path path) {

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());

        CsvSchema schema = csvMapper.schemaFor(PassportDataDto.class)
                .withHeader()
                .withColumnSeparator(';')
                .sortedBy("firstname", "lastname", "patronymic", "birthDate", "passportSeries", "passportNumber",
                        "gender", "placeOfBirth", "passportWasIssued", "dateOfIssue", "unitCode");

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {


            MappingIterator<PassportDataDto> dtoIterator = csvMapper
                    .readerFor(PassportDataDto.class)
                    .with(schema)
                    .readValues(reader);
            List<PassportDataDto> dto = dtoIterator.readAll();

            //Валидация пришедших данных по полям.
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                Validator validator = validatorFactory.getValidator();
                int rowNum = 1;
                for (PassportDataDto passportDataDto : dto) {
                    Set<ConstraintViolation<PassportDataDto>> violations = validator.validate(passportDataDto);
                    if (!violations.isEmpty()) {
                        throw new DataIntegrityViolationException("Следующие значение не являются валидной: \n" + "в строке " + rowNum +
                                                                  violations.stream().map(ConstraintViolation::getMessage).toList());
                    }
                    rowNum++;
                }
            }
            return dto;


        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }
}




