package parserFile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import parserFile.dto.PassportDataDto;
import parserFile.entity.PassportEntity;
import parserFile.mapper.PassportDataEntity;
import parserFile.mapper.FileMapperToDto;
import parserFile.repository.PassportRepository;

@Service
@RequiredArgsConstructor
public class PassportDataService {
    private final FileMapperToDto fileMapperToDto;
    private final PassportDataEntity dtoMapperToEntity;
    private final PassportRepository fileRepository;

    public void saveToDatabase(String[] data) {
        //Антипаттерн - названия методов ничего не значат
        //Понимание того, что делают методы приходит только от названия классов - это неправильно
        PassportDataDto fileDto = fileMapperToDto.apply(data);
        PassportEntity map = dtoMapperToEntity.apply(fileDto);
        fileRepository.save(map);
    }

}
