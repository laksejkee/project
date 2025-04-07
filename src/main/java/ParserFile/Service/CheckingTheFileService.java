package ParserFile.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//почему тут пробел? исправить.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * почему автовайред горит желтым?
 * модификаторы доступа
 * почитать про способы внедрения бинов
 * ФОРМАТИИРУЙ КОД CNTRL + ALT + L
 */
@Service
@RequiredArgsConstructor
public class CheckingTheFileService {

    @Autowired
    FileService fileService;

    public void checkAllFilesInDirectory(Path folderPath) {

        try (Stream<Path> list = Files.list(folderPath)) {
            list.filter(folderpath -> folderpath.getFileName().toString().endsWith(".txt"))
                    .forEach(path -> {
                        fileService.existsFile(path);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e); //почему ошибка не раскрыта? написать сообщение об ошибке
        }

    }

//вспомнить замечание ильи по методу
    public void checkOneFileInDirectory(Path path) throws IOException {
        if (path.getFileName().toString().endsWith(".txt")) {

            fileService.existsFile(path);
        }

    }
}

