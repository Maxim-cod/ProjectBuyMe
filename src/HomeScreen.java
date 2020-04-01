import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//finding elements in homeScreen
public class HomeScreen {
    WebDriver driver;
    @FindBy(css = ".seperator-link")
    WebElement login;
    @FindBy(css = ".ui-btn.orange.large")
    WebElement enterBuyMe;
    @FindBy(css=".text-btn")
    WebElement signUpScreen;
    @FindBy(css="form.form.ember-view div.ember-chosenselect:nth-of-type(1)")
    WebElement pricePoint;
    @FindBy(css="ul.chosen-results li[class=\"active-result\"]:nth-of-type(3)")
    WebElement price;
    @FindBy(css="form.form.ember-view div.ember-chosenselect:nth-of-type(2)")
    WebElement areaLocator;
    @FindBy(css = "div.form-control.dib:nth-of-type(2) li:nth-of-type(5)")
    WebElement location;
    @FindBy(css="form.form.ember-view div.ember-chosenselect:nth-of-type(3)")
    WebElement category;
    @FindBy(css = "div.form-control.dib:nth-of-type(3) li:nth-of-type(3)")
    WebElement selectCategory;
    @FindBy(css =".ui-btn.search.ember-view")
    WebElement findGift;
    @FindBy(css = ".recruiters-btn")
    WebElement elementInTheButtonOfPage;



    //constructors
    public HomeScreen (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //entering to login screen
    public void enterLoginScreen(){
        login.click();

    }
    //entering to signup screen
    public void enterSgnUpScreen(){
        signUpScreen.click();
    }
    //searching for gift
    public  void searchForGift() throws InterruptedException {
        pricePoint.click();
        Thread.sleep(1000);
        price.click();
        areaLocator.click();
        Thread.sleep(1000);
        location.click();
        category.click();
        Thread.sleep(1000);
        selectCategory.click();
        findGift.click();
    }
    //assert red text in login screen
    public void checkErrorRedTextInLoginScreen(){
        login.click();
        enterBuyMe.click();
    }

     //scroll to the button of the gift screen
      public void scrollToTheButtonOFScreen() throws InterruptedException {
          WebElement buttonDrop=elementInTheButtonOfPage;
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonDrop);
          WebDriverWait wait=new WebDriverWait(driver, 5);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".recruiters-btn")));
        }




    }








