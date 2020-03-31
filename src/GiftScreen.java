import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftScreen {
    WebDriver driver;
    //picking a buisness
    @FindBy(css = ".card-items a[class=\"card-item ember-view\"]:nth-of-type(3)")
    WebElement buisness;
    //picking gift from buisness
    @FindBy(css="div.wrapper.relative.top  div div[class=\"ember-view col col-1-3 col-md-1-2 text-right\"]:nth-of-type(3)")
    WebElement gift;


    //constructors
    public GiftScreen (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //pick a Gift in giftScreen
    public void pickBuisness() throws InterruptedException {
        buisness.click();
        Thread.sleep(1000);
        gift.click();
    }

}
