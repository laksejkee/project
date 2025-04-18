package parserFile.mapper;

import org.springframework.stereotype.Component;
import parserFile.dto.PassportDataDto;
import parserFile.utils.DateFormatters;
import java.time.LocalDate;
import java.util.function.Function;

@Component
public class FileMapperToDto implements Function<String[], PassportDataDto> {

    @Override
    public PassportDataDto apply(String[] string) {
        return new PassportDataDto(string[0],
                string[1],
                string[2],
                LocalDate.parse(string[3], DateFormatters.DD_MM_YYYY),
                Long.parseLong(string[4]),
                string[5],
                string[6],
                string[7],
                LocalDate.parse(string[8], DateFormatters.DD_MM_YYYY),
                Long.parseLong(string[9])
        );
    }
}
