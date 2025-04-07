package ParserFile.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * импорты не используются
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
@Service
public class ParseFileService {
    public Map<String, String> parseFile(Path path)  {
        Map<String, String> result = new HashMap<>();
        try {
            Files.lines(path) //почему горит желытм?
                    .filter(line -> line.contains(":"))
                    .forEach(line -> {
                        String[] parts = line.split(":");
                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();
                            result.put(key, value);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e); //сообщение об ошибке
        }
        return result;
    }
}
