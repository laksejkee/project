package ru.byrmistrov.fileParser.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.Optional;

@RequiredArgsConstructor
public enum FileType {
    XLSX("xlsx"),
    CSV("csv");

    private final String value;

    public String getValue() {
        return value;
    }

    public static Optional<FileType> getExtension(Path path) {
        String FileName = path.getFileName().toString();
        String extension = FilenameUtils.getExtension(FileName);
        for (FileType fileType : values()) {
            if (extension.endsWith(fileType.getValue())) {
                return Optional.of(fileType);
            }
        }
        return Optional.empty();
    }
}
