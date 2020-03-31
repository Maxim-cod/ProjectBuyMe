import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegestrationScreen {
    WebDriver driver;
    @FindBy(css=".option.oldschool  form div:nth-of-type(1) label input")
    WebElement userName;
    @FindBy(css=".option.oldschool  form div:nth-of-type(2) label input")
    WebElement email;
    @FindBy(id="valPass") WebElement password;
    @FindBy(css = ".option.oldschool  form div:nth-of-type(4) label input")
    WebElement confirmPassword;
    @FindBy(css = ".ui-btn.orange.large")
    WebElement register;

    //constructors
    public RegestrationScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //fill the information in restration screen
    public void enterRegister() throws Exception {
        userName.sendKeys(General.readFromFile("userName"));
        email.sendKeys(General.readFromFile("email"));
        password.sendKeys(General.readFromFile("password"));
        confirmPassword.sendKeys(General.readFromFile("password"));
        register.click();

    }



}
