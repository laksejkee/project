package ParserFile.Mapper;

import ParserFile.Entity.FileEntity;
import ParserFile.Service.FileService;
import ParserFile.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class FileReadMapper implements Mapper<Map<String, String>, FileDto> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Override
    public FileDto map(Map<String, String> object) {
        FileDto fileDto = new FileDto();
        fileDto.setFirstname( object.get("Имя"));
        fileDto.setLastname( object.get("Фамилия"));
        fileDto.setPatronymic( object.get("Отчество"));
        fileDto.setBirthDate(LocalDate.parse(object.get("Дата рождения"), formatter));
        fileDto.setPassportNumber(Long.parseLong(object.get("Серия и номер паспорта")));
        fileDto.setSex(object.get("Пол"));
        fileDto.setBirthCity(object.get("Место рождения"));
        fileDto.setPassportWasIssued(object.get("Паспорт выдан"));
        fileDto.setSubdivisionCode(LocalDate.parse(object.get("Дата выдачи"), formatter));
        fileDto.setUnitCode(Long.parseLong(object.get("Код подразделения")));
        return fileDto;
    }
}



