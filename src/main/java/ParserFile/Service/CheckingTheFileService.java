package ParserFile.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

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
                    throw new RuntimeException(e);
                }

        }


    public void checkOneFileInDirectory(Path path) throws IOException {
        if (path.getFileName().toString().endsWith(".txt")) {

            fileService.existsFile(path);
        }

    }
}

