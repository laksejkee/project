package ru.byrmistrov.fileParser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.dto.HasPassportNumber;
import ru.byrmistrov.fileParser.service.parser.FileParser;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileScanner {

    private final Map<String, FileParser> parserMap;

    public List<? extends HasPassportNumber> parseFile(Path filePath) {
        FileParser fileParser = FileType.getExtension(filePath)
                .flatMap(fileType -> Optional.ofNullable(parserMap.get(fileType.getValue())))
                .orElseThrow(() -> new RuntimeException(" Не найден парсер для файла: " + filePath));
        return fileParser.parseFile(filePath);
    }
}
