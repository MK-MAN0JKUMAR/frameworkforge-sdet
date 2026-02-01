package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UserRegistrationPage extends BasePageObjects {

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
        click(registerLink);
    }

    public void register(
            String first,
            String last,
            String mail,
            String phone,
            String pwd,
            String occupationValue) {

        type(firstName, first);
        type(lastName, last);
        type(email, mail);
        type(mobile, phone);

        new Select(occupation).selectByVisibleText(occupationValue);

        click(male);
        type(password, pwd);
        type(confirmPassword, pwd);
        click(ageCheckbox);
        click(registerBtn);
    }

    public boolean isSuccessMessageVisible() {
        return successMessage.isDisplayed();
    }

    public void goToLogin() {
        click(loginBtn);
    }
}
