package framework.tests;

import framework.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTestClass;
import framework.pages.MyHomePage;
import framework.pages.UserLoginPage;

public class UserLoginTest extends BaseTestClass {

	@Test(groups = "smoke")
	public void userShouldLoginSuccessfully() {
		logger.info("Executing test: userShouldLoginSuccessfully...");

		String email = ConfigReader.get("smoke.user.email");
		String password = ConfigReader.get("smoke.user.password");

		UserLoginPage login = new UserLoginPage();
		Assert.assertTrue(login.isAt(), "Login page not loaded");

		logger.info("Login test started");
		login.login(email, password);
		logger.info("User logged in successfully");


		MyHomePage home = new MyHomePage();
		Assert.assertTrue(home.isLoaded(), "Login failed");

		home.logout();
		logger.info("Executed test Successfully: userShouldLoginSuccessfully...");
	}
}
