package ParserFile.Mapper;

import ParserFile.Entity.FileEntity;
import ParserFile.dto.FileDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapperToEntity implements Mapper<FileDto, FileEntity>{
    @Override
    public FileEntity map(FileDto object) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFirstname(object.getFirstname());
        fileEntity.setLastname(object.getLastname());
        fileEntity.setPatronymic(object.getPatronymic());
        fileEntity.setPassportNumber(object.getPassportNumber());
        fileEntity.setBirthDate(object.getBirthDate());
        fileEntity.setSex(object.getSex());
        fileEntity.setBirthCity(object.getBirthCity());
        fileEntity.setPassportWasIssued(object.getPassportWasIssued());
        fileEntity.setSubdivisionCode(object.getSubdivisionCode());
        fileEntity.setUnitCode(object.getUnitCode());
        return fileEntity;
    }
}
