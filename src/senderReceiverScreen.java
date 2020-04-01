import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class senderReceiverScreen {
    WebDriver driver;
    String picturePath="D:\\games\\jpg\\maxresdefault.jpg";
    @FindBy(css="label.option.selected")
    WebElement radioButtonSomeOneElse;
    @FindBy(css="label[class=\"ember-view ui-field ui-input\"]:nth-of-type(1)")
    WebElement forWhoTheGift;
    @FindBy(css="input[data-parsley-required-message=\"למי יגידו תודה? שכחת למלא את השם שלך\"]")
    WebElement fromWhoTheGift;
    @FindBy(css="div.step-form-wrapper.relative div.mx2.md1:nth-of-type(3) label[class=\"ember-view ui-field ui-select\"] div  a.chosen-single")
    WebElement whichEventFromDropDownBox;
    @FindBy(css = "ul.chosen-results li[class=\"active-result\"]:nth-of-type(3)")
    WebElement pickEventFromDropDownBox;
    @FindBy(css = "textarea[rows=\"4\"]")
    WebElement blessing;
    @FindBy(name = "fileUpload")
    WebElement uploadPicture;
    @FindBy(css = "label.send-now")
    WebElement sendNowRadioButton;
    @FindBy(css = "span.icon.icon-envelope")
    WebElement pickEmailOption;
    @FindBy(css = "input.form-control.input-theme.input-mail.ember-view.ember-text-field")
    WebElement insertEmail;
    @FindBy(css = "button.btn.btn-theme.btn-save")
    WebElement saveMail;
    @FindBy(css = "button.ui-btn.orange.large")
    WebElement submit;
    @FindBy(css = "a[class=\"dropdown-item\"] span")
    WebElement exit ;







    //constructors
    public senderReceiverScreen (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //fill the information in senderAndReceiverPage
    public void senderAndReceiverInformation() throws Exception {
        boolean selected_value = radioButtonSomeOneElse.isSelected();
        if (!selected_value) ;
        radioButtonSomeOneElse.click();
        forWhoTheGift.sendKeys(General.readFromFile("forWhoTheGift"));
        fromWhoTheGift.clear();
        fromWhoTheGift.sendKeys(General.readFromFile("fromWhoTheGift"));
        Thread.sleep(1000);
        whichEventFromDropDownBox.click();
        Thread.sleep(1000);
        pickEventFromDropDownBox.click();
        Thread.sleep(1000);
        blessing.clear();
        blessing.sendKeys(General.readFromFile("blessing"));
        General.uploadPicture(uploadPicture, picturePath);
        WebElement buttonDrop = uploadPicture;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonDrop);
        Thread.sleep(1000);
        boolean selected_value2 = sendNowRadioButton.isSelected();
        if (!selected_value) ;
        sendNowRadioButton.click();
        pickEmailOption.click();
        insertEmail.sendKeys(General.readFromFile("insertEmail"));
        saveMail.click();
        submit.click();

    }

      //exit from my account
      public void exitAccount() throws InterruptedException {
          Thread.sleep(2000);
          Actions builder = new Actions(driver);
          WebElement mouseOverHome = driver.findElement(By.cssSelector("ul.nav-bar.buttons li a span img.arrow"));
          builder.moveToElement(mouseOverHome).perform();
          exit.click();












    }

}
