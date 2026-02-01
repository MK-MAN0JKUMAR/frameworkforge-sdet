package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLoginPage extends BasePageObjects {

	@FindBy(id = "userEmail")
	private WebElement email;

	@FindBy(id = "userPassword")
	private WebElement password;

	@FindBy(id = "login")
	private WebElement loginBtn;

	@FindBy(xpath = "//h1[text()='Log in']")
	private WebElement header;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	private WebElement registerBtn;

	@FindBy(xpath = "//div[contains(text(),' Incorrect email or password.')]")
	private WebElement loginError;

	public boolean isAt() {
		return header.isDisplayed();
	}

	public void login(String user, String pwd) {
		type(email, user);
		log.info("Entering email: {}", email);

		type(password, pwd);
		log.info("Entering email: {}", password);

		click(loginBtn);
		log.info("Clicking login button");
	}

	public boolean isLoginErrorVisible() {
		try {
			return loginError.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
