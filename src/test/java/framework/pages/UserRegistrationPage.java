package framework.pages;

import framework.driver.DriverManager;
import framework.utils.WaitUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserRegistrationPage {

    private static final Logger log = LogManager.getLogger(UserRegistrationPage.class);

    public UserRegistrationPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement registerLink;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "userMobile")
    private WebElement mobile;

    @FindBy(xpath = "//select[@formcontrolname='occupation']")
    private WebElement occupation;

    @FindBy(xpath = "//input[@value='Male']")
    private WebElement male;

    @FindBy(id = "userPassword")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement ageCheckbox;

    @FindBy(id = "login")
    private WebElement registerBtn;

    @FindBy(xpath = "//h1[normalize-space()='Account Created Successfully']")
    private WebElement successMessage;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginBtn;

    public void openRegistration() {
        WaitUtil.click(registerLink, 10);
    }

    public void register(
            String first,
            String last,
            String mail,
            String phone,
            String pwd,
            String occupationValue) {

        WaitUtil.type(firstName, first);
        WaitUtil.type(lastName, last);
        WaitUtil.type(email, mail);
        WaitUtil.type(mobile, phone);

        new Select(occupation).selectByVisibleText(occupationValue);

        WaitUtil.click(male);
        WaitUtil.type(password, pwd);
        WaitUtil.type(confirmPassword, pwd);
        WaitUtil.click(ageCheckbox);
        WaitUtil.click(registerBtn);
    }

    public boolean isSuccessMessageVisible() {
        return successMessage.isDisplayed();
    }

    public void goToLogin() {
        WaitUtil.click(loginBtn, 10);
    }
}
