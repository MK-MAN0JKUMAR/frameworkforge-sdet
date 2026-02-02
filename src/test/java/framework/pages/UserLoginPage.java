package framework.pages;

import framework.driver.DriverManager;
import framework.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage {

	private static final Logger log = LogManager.getLogger(UserLoginPage.class);

	public UserLoginPage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

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
		WaitUtil.type(email, user);
		WaitUtil.type(password, pwd);
		WaitUtil.click(loginBtn);
	}

	public boolean isLoginErrorVisible() {
		try {
			return loginError.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
