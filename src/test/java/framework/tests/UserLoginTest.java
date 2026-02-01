package framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTestClass;
import framework.pages.MyHomePage;
import framework.pages.UserLoginPage;

public class UserLoginTest extends BaseTestClass {

	@Test(groups = "smoke")
	public void userShouldLoginSuccessfully() {

		String email = properties.getProperty("smoke.user.email");
		String password = properties.getProperty("smoke.user.password");

		UserLoginPage login = new UserLoginPage();
		Assert.assertTrue(login.isAt(), "Login page not loaded");

		login.login(email, password);

		MyHomePage home = new MyHomePage();
		Assert.assertTrue(home.isLoaded(), "Login failed");

		home.logout();
	}
}
