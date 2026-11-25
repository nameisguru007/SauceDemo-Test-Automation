
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {

    public static Object[][] getExcelData(
            String filePath, String sheetName) throws IOException {

        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            // Print available sheet names
            System.out.println("Available Excel sheets:");

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println(workbook.getSheetName(i));
            }

            Sheet sheet = workbook.getSheet(sheetName);

            // Validate sheet
            if (sheet == null) {
                throw new RuntimeException(
                    "Sheet '" + sheetName + "' not found in Excel file!"
                );
            }

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String username = formatter.formatCellValue(
                    row.getCell(0));

                String password = formatter.formatCellValue(
                    row.getCell(1));

                String expected = formatter.formatCellValue(
                    row.getCell(2));

                if (username.isBlank() ||
                    password.isBlank() ||
                    expected.isBlank()) {
                    continue;
                }

                data.add(new Object[] {
                    username, password, expected
                });
            }
        }

        return data.toArray(new Object[0][]);
    }
}
