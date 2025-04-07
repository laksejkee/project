package ParserFile.Service;

import ParserFile.Entity.FileEntity;
import ParserFile.Mapper.FileMapperToEntity;
import ParserFile.Mapper.FileReadMapper;
import ParserFile.Repository.FileRepository;
import ParserFile.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//тут пробел
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * внедрение через поля
 * модификаторы доступа
 */
@Service
@RequiredArgsConstructor
public class FileService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileReadMapper fileReadMapper;
    @Autowired
    FileMapperToEntity fileMapperToEntity;
    @Autowired
    ParseFileService parseFileService;

    public  void existsFile(Path path)  {
        try {
            if (Files.exists(path) && Files.size(path) != 0) {
                Map<String, String> dataMap = parseFileService.parseFile(path);
                FileDto fileDto = fileReadMapper.map(dataMap);
                FileEntity entity = fileMapperToEntity.map(fileDto);
                fileRepository.save(entity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //сообщение об ошибке
        }
    }

}
