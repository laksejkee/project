package ru.byrmistrov.fileParser.dto;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.byrmistrov.fileParser.service.converter.LocalDateDeserializerCsv;

import java.time.LocalDate;

public record PassportDataDto(
        @NotBlank(message = " 'Имя' не может быть пустым")
        String firstname,
        @NotBlank(message = " 'Фамилия' не может быть пустым")
        String lastname,
        @NotBlank(message = " 'Отчество' не может быть пустым")
        String patronymic,
        @NotNull(message = " 'Дата рождения' обязательна")
        @JsonDeserialize(using = LocalDateDeserializerCsv.class)
        @DateTimeFormat("dd.MM.yyyy")
        LocalDate birthDate,
        @NotBlank(message = " 'Серия паспорта' не может быть пустым")
        @Size(min = 4, max = 4, message = " 'Серия паспорта' быть 4 символа")
        @Pattern(regexp = "\\d{4}", message = " 'Серия паспорта' должна содержать только цифры")
        String passportSeries,
        @NotBlank(message = " 'Номер паспорта' не может быть пустым")
        @Size(min = 6, max = 6, message = " 'Номер паспорта' должен быть 6 символов")
        @Pattern(regexp = "\\d{6}", message = " 'Номер паспорта' должен содержать только цифры")
        String passportNumber,
        @NotBlank(message = " 'Пол' не может быть пустым")
        @Pattern(regexp = "мужской|женский", message = " 'Пол' должен быть 'мужской' или 'женский'")
        String gender,
        @NotBlank(message = " 'Место рождения' не может быть пустым")
        String placeOfBirth,
        @NotBlank(message = " 'Паспорт выдан' не может быть пустым")
        String passportWasIssued,
        @NotNull(message = " 'Дата выдачи' обязательна")
        @JsonDeserialize(using = LocalDateDeserializerCsv.class)
        @DateTimeFormat("dd.MM.yyyy")
        LocalDate dateOfIssue,
        @NotBlank(message = " 'Код подразделения' не может быть пустым")
        @Pattern(regexp = "\\d{3}-\\d{3}", message = " 'Код подразделения' должен быть в формате xxx-xxx")
        String unitCode
) implements HasPassportNumber {
    @Override
    public String getPassportNumber() {
        return passportNumber;
    }
}

