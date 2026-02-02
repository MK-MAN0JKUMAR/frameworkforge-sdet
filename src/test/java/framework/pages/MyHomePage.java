package framework.pages;

import framework.driver.DriverManager;
import framework.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyHomePage {

	private static final Logger log = LogManager.getLogger(MyHomePage.class);

	public MyHomePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	@FindBy(xpath = "//section[@id='sidebar']//p[1]")
	private WebElement homeText;

	@FindBy(xpath = "//button[normalize-space()='Sign Out']")
	private WebElement logoutBtn;

	public boolean isLoaded() {
		try {
			return homeText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void logout() {
		WaitUtil.click(logoutBtn, 10);
	}
}
