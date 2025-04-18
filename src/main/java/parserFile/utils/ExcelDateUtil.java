package parserFile.utils;

import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import java.text.SimpleDateFormat;

@NoArgsConstructor
public final class ExcelDateUtil {
    public static String formatCellAsDate(Cell cell) {
        return new SimpleDateFormat("dd.MM.yyyy").format(cell.getDateCellValue());
    }
}
