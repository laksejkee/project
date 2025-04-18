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
        PassportDataDto fileDto = fileMapperToDto.apply(data);
        PassportEntity map = dtoMapperToEntity.apply(fileDto);
        fileRepository.save(map);
    }

}
