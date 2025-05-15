package ru.byrmistrov.fileParser.service.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Service;
import ru.byrmistrov.fileParser.utils.DateFormatters;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class LocalDateDeserializerCsv extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        return LocalDate.parse(date, DateFormatters.DD_MM_YYYY);
    }
}
