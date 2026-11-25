
package dataproviders;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import utils.ExcelUtils;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() throws IOException {

        // Get project directory
        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/LoginData.xlsx";

        File file = new File(path);

        // Check Excel file
        if (!file.exists()) {
            throw new RuntimeException(
                "Excel file not found: " + file.getAbsolutePath()
            );
        }

        System.out.println("Excel file found: "
                + file.getAbsolutePath());

        // Read Excel data
        Object[][] data = ExcelUtils.getExcelData(
                path, "LoginData"
        );

        // Verify records
        System.out.println("Total Excel rows: " + data.length);

        if (data.length == 0) {
            throw new RuntimeException(
                "No test data found in LoginData sheet!"
            );
        }

        return data;
    }
}
