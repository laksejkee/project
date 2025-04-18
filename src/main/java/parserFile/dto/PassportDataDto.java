package parserFile.dto;

import java.time.LocalDate;

public record PassportDataDto(String firstname,
                              String lastname,
                              String patronymic,
                              LocalDate birthDate,
                              Long passportNumber,
                              String sex,
                              String birthCity,
                              String passportWasIssued,
                              LocalDate subdivisionCode,
                              Long unitCode

){}

