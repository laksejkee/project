package parserFile.utils;

import lombok.NoArgsConstructor;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public final class DateFormatters {
    public static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

}