package ru.byrmistrov.fileParser.service.parser;

import ru.byrmistrov.fileParser.dto.HasPassportNumber;

import java.nio.file.Path;
import java.util.List;

public interface FileParser {
    List<? extends HasPassportNumber> parseFile(Path path);
}
