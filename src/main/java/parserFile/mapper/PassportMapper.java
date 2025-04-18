package parserFile.mapper;

import org.springframework.stereotype.Component;
import parserFile.dto.PassportDataDto;
import parserFile.entity.PassportEntity;
import parserFile.utils.DateFormatters;

import java.time.LocalDate;

@Component
public class PassportMapper {

    public PassportDataDto mapRawDataToDTO(String[] rawData) {
        return new PassportDataDto(rawData[0],
                rawData[1],
                rawData[2],
                LocalDate.parse(rawData[3], DateFormatters.DD_MM_YYYY),
                Long.parseLong(rawData[4]),
                rawData[5],
                rawData[6],
                rawData[7],
                LocalDate.parse(rawData[8], DateFormatters.DD_MM_YYYY),
                Long.parseLong(rawData[9])
        );
    }

    public PassportEntity mapToEntity(PassportDataDto fileDto) {
        PassportEntity fileEntity = new PassportEntity();
        fileEntity.setFirstname(fileDto.firstname());
        fileEntity.setLastname(fileDto.lastname());
        fileEntity.setPatronymic(fileDto.patronymic());
        fileEntity.setPassportNumber(fileDto.passportNumber());
        fileEntity.setBirthDate(fileDto.birthDate());
        fileEntity.setSex(fileDto.sex());
        fileEntity.setBirthCity(fileDto.birthCity());
        fileEntity.setPassportWasIssued(fileDto.passportWasIssued());
        fileEntity.setSubdivisionCode(fileDto.subdivisionCode());
        fileEntity.setUnitCode(fileDto.unitCode());
        return fileEntity;
    }
}
