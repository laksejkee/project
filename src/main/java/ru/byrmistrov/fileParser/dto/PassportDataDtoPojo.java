package ru.byrmistrov.fileParser.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.byrmistrov.fileParser.service.converter.LocalDateConverterXlsx;

import java.time.LocalDate;

@Data
public class PassportDataDtoPojo implements HasPassportNumber {
    @NotBlank(message = " 'Имя' не может быть пустым")
    @ExcelProperty("Имя")
    private String firstname;

    @NotBlank(message = " 'Фамилия' не может быть пустым")
    @ExcelProperty("Фамилия")
    private String lastname;

    @NotBlank(message = " 'Отчество' не может быть пустым")
    @ExcelProperty("Отчество")
    private String patronymic;

    @NotNull(message = " 'Дата рождения' обязательна")
    @DateTimeFormat("dd.MM.yyyy")
    @ExcelProperty(value = "Дата рождения", converter = LocalDateConverterXlsx.class)
    private LocalDate birthDate;

    @NotBlank(message = " 'Серия паспорта' не может быть пустым")
    @Size(min = 4, max = 4, message = " 'Серия паспорта' должна быть 4 символа")
    @Pattern(regexp = "\\d{4}", message = " 'Серия паспорта' должна содержать только цифры")
    @ExcelProperty(value = "Серия паспорта")
    private String passportSeries;

    @NotBlank(message = " 'Номер паспорта' не может быть пустым")
    @Size(min = 6, max = 6, message = " 'Номер паспорта' должен быть 6 символов")
    @Pattern(regexp = "\\d{6}", message = " 'Номер паспорта' должен содержать только цифры")
    @ExcelProperty(value = "Номер паспорта")
    private String passportNumber;

    @NotBlank(message = " 'Пол' не может быть пустым")
    @Pattern(regexp = "мужской|женский", message = " 'Пол' должен быть 'мужской' или 'женский'")
    @ExcelProperty("Пол")
    private String gender;

    @NotBlank(message = " 'Место рождения' не может быть пустым")
    @DateTimeFormat("dd.MM.yyyy")
    @ExcelProperty("Место рождения")
    private String placeOfBirth;

    @NotBlank(message = " 'Паспорт выдан' не может быть пустым")
    @ExcelProperty("Паспорт выдан")
    private String passportWasIssued;


    @NotNull(message = " 'Дата выдачи' обязательна")
    @DateTimeFormat("dd.MM.yyyy")
    @ExcelProperty(value = "Дата выдачи", converter = LocalDateConverterXlsx.class)
    private LocalDate dateOfIssue;

    @NotBlank(message = " 'Код подразделения' не может быть пустым")
    @Pattern(regexp = "\\d{3}-\\d{3}", message = "Код подразделения должен быть в формате xxx-xxx")
    @ExcelProperty("Код подразделения")
    private String unitCode;


    @Override
    public String getPassportNumber() {
        return passportNumber;
    }
}
