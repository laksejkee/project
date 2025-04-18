package parserFile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;

/**
 * Переименовать в соответствии с:
 * {@link parserFile.dto.PassportDataDto}
 */
@Data
@Entity
@Table(name = "passport_date")
public class PassportEntity {
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
}
