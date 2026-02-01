package framework.tests;

import framework.base.BaseTestClass;
import framework.pages.MyHomePage;
import framework.pages.UserLoginPage;
import framework.dataproviders.DataProviders;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLoginTest_DDT extends BaseTestClass {

    @Test(
            dataProvider = "loginExcelData",
            dataProviderClass = DataProviders.class,
            groups = {"regression"}
    )
    public void loginWithMultipleUsers(String email,
                                       String password,
                                       String expectedResult) {

        UserLoginPage login = new UserLoginPage();
        login.login(email, password);

        MyHomePage home = new MyHomePage();

        if (expectedResult.equalsIgnoreCase("valid")) {
            Assert.assertTrue(home.isLoaded(), "Home page should be visible for valid user");
            home.logout();
        } else {
            Assert.assertFalse(login.isLoginErrorVisible(), "Error message should be visible for invalid login");
        }
    }
}
