package framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTestClass;
import framework.pages.UserLoginPage;
import framework.pages.UserRegistrationPage;
import framework.utils.DataGenerator;

public class UserRegistrationTest extends BaseTestClass {

    @Test(groups = "sanity")
    public void userShouldRegisterSuccessfully() {

        String password = properties.getProperty("smoke.user.passwrod");

        UserLoginPage loginPage = new UserLoginPage();
        Assert.assertTrue(loginPage.isAt(), "Login page not loaded");

        UserRegistrationPage registerPage = new UserRegistrationPage();
        registerPage.openRegistration();

        registerPage.register(
                DataGenerator.alphabetic(5),
                DataGenerator.alphabetic(5),
                DataGenerator.alphanumeric(10) + "@gmail.com",
                "1" + DataGenerator.numeric(9),
                password,
                "Engineer"
        );

        Assert.assertTrue(registerPage.isSuccessMessageVisible(),
                "Registration success message not displayed");

        registerPage.goToLogin();
    }
}
