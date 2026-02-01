package framework.dataproviders;

import java.io.IOException;

import framework.utils.ExcelUtility;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{{"boata4@fmail.com", "Test1234"}};
    }

    // DataProvider 1
    @DataProvider(name = "loginExcelData")
    public Object[][] loginData() throws Exception {

        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/loginData.xlsx";

        ExcelUtility excel = new ExcelUtility(path);
        return excel.readSheet("Sheet1");
    }

    // DataProvider 3

    // DataProvider 4

    // DataProvider 5
}
