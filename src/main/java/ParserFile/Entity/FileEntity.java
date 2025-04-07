package ParserFile.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passport_date")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private LocalDate birthDate;
    private Long passportNumber;
    private String sex;
    private String birthCity;
    private String passportWasIssued;
    private LocalDate subdivisionCode;
    private Long unitCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getPassportWasIssued() {
        return passportWasIssued;
    }

    public void setPassportWasIssued(String passportWasIssued) {
        this.passportWasIssued = passportWasIssued;
    }

    public LocalDate getSubdivisionCode() {
        return subdivisionCode;
    }

    public void setSubdivisionCode(LocalDate subdivisionCode) {
        this.subdivisionCode = subdivisionCode;
    }

    public Long getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(Long unitCode) {
        this.unitCode = unitCode;
    }
}
