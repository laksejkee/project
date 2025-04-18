package parserFile.dto;

import java.time.LocalDate;

/**
 * TODO: НАВЕСИТЬ АННОТАЦИИ jakarta-валидации.
 * @param firstname
 * @param lastname
 * @param patronymic
 * @param birthDate
 * @param passportNumber
 * @param sex
 * @param birthCity
 * @param passportWasIssued
 * @param subdivisionCode
 * @param unitCode
 */
public record PassportDataDto(
        String firstname,
        String lastname,
        String patronymic,
        LocalDate birthDate,
        //TODO: серия и номер раздельно.
        Long passportNumber,
        //TODO: gender.
        String sex,
        //TODO: место рождения
        String birthCity,
        String passportWasIssued,
        //TODO: неправильный тип данных. Переименовать в дату выдачи
        LocalDate subdivisionCode,
        //TODO: код подразделения в формате ddd-ddd. неправильный тип данных
        Long unitCode
){

}

