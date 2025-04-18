package parserFile.mapper;

import parserFile.entity.PassportEntity;
import parserFile.dto.PassportDataDto;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class PassportDataEntity implements Function<PassportDataDto, PassportEntity> {
    @Override
    public PassportEntity apply(PassportDataDto fileDto) {
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
