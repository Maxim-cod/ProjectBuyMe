import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;
//    @FindBy(css = ".option.oldschool  form div:nth-of-type(1) label input")
//    WebElement email;
//    @FindBy(css = ".option.oldschool  form div:nth-of-type(2) label input")
//    WebElement password;
//    @FindBy(css = ".ui-btn.orange.large")
//    WebElement enterByMe;

    //constructors
    public Login(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

//    public void enter() throws Exception{
//        email.sendKeys(General.readFromFile("email"));
//        password.sendKeys(General.readFromFile("password"));
//        enterByMe.click();
    }



