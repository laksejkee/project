package parserFile.mapper;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import parserFile.utils.ExcelDateUtil;
import java.util.function.Function;

//Перенести специфичную для файла логику в класс обработчик этого типа файлов
@Component
public class XlsxDataMapperToString implements Function<Row, String[]> {

    @Override
    public String[] apply(Row row) {

        DataFormatter dataFormatter = new DataFormatter();
        String[] rowData = new String[row.getLastCellNum()];
        rowData[0] = dataFormatter.formatCellValue(row.getCell(0));
        rowData[1] = dataFormatter.formatCellValue(row.getCell(1));
        rowData[2] = dataFormatter.formatCellValue(row.getCell(2));
        rowData[3] = ExcelDateUtil.formatCellAsDate(row.getCell(3));
        rowData[4] = String.valueOf(Long.parseLong(dataFormatter.formatCellValue(row.getCell(4))));
        rowData[5] = dataFormatter.formatCellValue(row.getCell(5));
        rowData[6] = dataFormatter.formatCellValue(row.getCell(6));
        rowData[7] = dataFormatter.formatCellValue(row.getCell(7));
        rowData[8] = ExcelDateUtil.formatCellAsDate(row.getCell(8));
        rowData[9] = String.valueOf(Long.parseLong(dataFormatter.formatCellValue(row.getCell(9))));
        return rowData;
    }
}
