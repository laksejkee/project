package ru.byrmistrov.fileParser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.Mark;
import ru.byrmistrov.fileParser.entity.PassportDataEntity;
import ru.byrmistrov.fileParser.mapper.PassportDataMapper;
import ru.byrmistrov.fileParser.repository.PassportRepository;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassportDataService {
    private final FileScanner fileScanner;
    private final PassportDataMapper passportDataMapper;
    private final PassportRepository passportRepository;

    public void saveToDatabase(Path filePath) {

        List<? extends Mark> marks = fileScanner.parseFile(filePath);
        List<PassportDataEntity> entity = passportDataMapper.toEntity(marks);
        passportRepository.saveAll(entity);
    }

}
