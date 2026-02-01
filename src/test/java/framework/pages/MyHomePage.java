package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyHomePage extends BasePageObjects {

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
		click(logoutBtn);
	}
}
