package ru.byrmistrov.fileParser.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "passport_date")
public class PassportDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private LocalDate birthDate;
    private String passportSeries;
    private String passportNumber;
    private String gender;
    private String placeOfBirth;
    private String passportWasIssued;
    private LocalDate DateOfIssue;
    private String unitCode;
}
