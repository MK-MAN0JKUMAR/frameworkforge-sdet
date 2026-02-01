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
        logger.info("Executing test: userShouldRegisterSuccessfully...");

        String password = properties.getProperty("smoke.user.password");

        UserLoginPage loginPage = new UserLoginPage();
        Assert.assertTrue(loginPage.isAt(), "Login page not loaded");

        UserRegistrationPage registerPage = new UserRegistrationPage();
        registerPage.openRegistration();

        logger.info("Registration test started");
        registerPage.register(
                DataGenerator.alphabetic(5),
                DataGenerator.alphabetic(5),
                DataGenerator.alphanumeric(10) + "@gmail.com",
                "9" + DataGenerator.numeric(9),
                password,
                "Engineer"
        );
        logger.info("Registration completed successfully");


        Assert.assertTrue(registerPage.isSuccessMessageVisible(),
                "Registration success message not displayed");

        registerPage.goToLogin();

        logger.info("Executed test Successfully: userShouldRegisterSuccessfully...");

    }
}
