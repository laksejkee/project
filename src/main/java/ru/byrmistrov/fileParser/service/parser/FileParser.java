package ru.byrmistrov.fileParser.service.parser;

import ru.byrmistrov.fileParser.dto.Mark;

import java.nio.file.Path;
import java.util.List;

public interface FileParser {
    List<? extends Mark> parseFile(Path path);
}
