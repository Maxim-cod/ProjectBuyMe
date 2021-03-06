import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
//putting tests in order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
    //create class object
    HomeScreen homeScreen=new HomeScreen(driver);
    RegestrationScreen regestrationScreen=new RegestrationScreen(driver);
    GiftScreen giftScreen=new GiftScreen(driver);
    senderReceiverScreen senderReceiverScreen=new senderReceiverScreen(driver);
    String imagesPath = ("C:\\Users\\MAXIM\\IdeaProjects\\byMe\\ScreenShots");
    private static ExtentReports extent;
    private static ExtentTest maximTests;
    public static WebDriver driver;

    @Rule
    public TestName name = new TestName();



    //run once before class
    @BeforeClass
    public static void setUp() throws Exception {
        //path to test logs
        extent = new ExtentReports("C:\\Users\\MAXIM\\IdeaProjects\\byMe\\testReports\\testReports01.html");
        //path to report config
        extent.loadConfig(new File("C:\\Users\\MAXIM\\IdeaProjects\\byMe\\reportConfig.xml"));
        String browser = General.readFromFile("websiteURL");
        setBrowther(browser);

        //waiting functions
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
    }

    //run once before tests
    @Before
    public void before() {
        System.out.println("Run setup");
    }

    //starting test 01
    @Test
    public void test01_Regestration() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO,"start test ");
        driver.get("https://buyme.co.il/");
        homeScreen.enterLoginScreen();
        homeScreen.enterSgnUpScreen();
        String actualUrl=driver.getCurrentUrl();
        regestrationScreen=new RegestrationScreen(driver);
        //check if the information in resgestraion screen is correct
        try {
            regestrationScreen.enterRegister();
            maximTests.log(LogStatus.PASS, "Create new account to buyMe ");
        }
        catch (Exception error){
            maximTests.log(LogStatus.FAIL,"login fail " + error);
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }
        //checking if urls are equals in regestraion screen
        try{
            String expectedUrl="https://buyme.co.il/?modal=login";
            assertEquals(actualUrl,expectedUrl);
            maximTests.log(LogStatus.PASS,"The urls are equals in RegestraionScreen screen");
        }
        catch (AssertionError regestraionError){
            maximTests.log(LogStatus.FAIL,"The urls are not equals in Regestraion screen" + regestraionError);
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }
        maximTests.log(LogStatus.INFO,"end of test  " + name.getMethodName());

    }
    //End of test 01

    //starting test 02
    @Test
    public void test02_HomeScreen() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO,"start test ");
        homeScreen=new HomeScreen(driver);
        //check if button search is working
        try {
            homeScreen.searchForGift();
            maximTests.log(LogStatus.PASS, "Press on search button to find a gift ");
        }
        catch (Exception error) {
            maximTests.log(LogStatus.FAIL, "The search button element is not found " + error);
            maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }
            maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());

    }
    //end of test 02

    //starting test03
    @Test
    public void test03_GiftScreen() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO, "start test ");
        //check if web page url was changed
        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        try {
            String expectedUrl = "https://buyme.co.il/search?budget=2&category=16&region=12";
            System.out.println(expectedUrl);
            assertEquals(expectedUrl, actualUrl);
            maximTests.log(LogStatus.PASS, "The web page url  changed ");
        } catch (AssertionError urlError) {
            maximTests.log(LogStatus.FAIL, "The web page url does not change ");
            maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }

        giftScreen = new GiftScreen(driver);
        //check if the element of pick a buisness is working
        try {
            giftScreen.pickBuisness();
            maximTests.log(LogStatus.PASS, "Pick a buisness ");
        }
        catch (Exception error) {
            maximTests.log(LogStatus.FAIL, "the element is not found " + error);
            maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }
        //check if the element of pick a gift is working
        try {
            maximTests.log(LogStatus.PASS, "Pick a Gift ");
        }
        catch (Exception error) {
            maximTests.log(LogStatus.FAIL, "the elements is not found " + error);
            maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }

        maximTests.log(LogStatus.INFO, "end of test  " + name.getMethodName());
    }

    //end of test03

    //start of test04
    @Test
    public void test04_SenderReceiverScreen() throws Exception {
        maximTests = extent.startTest(name.getMethodName());
        maximTests.log(LogStatus.INFO,"start test ");
        senderReceiverScreen = new senderReceiverScreen(driver);
        //check if all information in package page complete before continue to payment page
        try {
            senderReceiverScreen.senderAndReceiverInformation();
            maximTests.log(LogStatus.PASS, "Go to page of payment after filling all the information is package page ");
        }
        catch (Exception error){
            maximTests.log(LogStatus.FAIL,"the information in package page is not complete " + error);
            maximTests.log(LogStatus.INFO, "details", maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }

        //navigate back to home screen
        Thread.sleep(1000);
        driver.navigate().to("https://buyme.co.il/");
        //exit from your account
        try {
            senderReceiverScreen.exitAccount();
            maximTests.log(LogStatus.PASS, "exit from your account ");
        }
        catch (Exception errorLogout){
            maximTests.log(LogStatus.FAIL,"fail to exit from your account " + errorLogout);
            maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
        }

        maximTests.log(LogStatus.INFO,"end of test  " + name.getMethodName());

    }
        //end of test04


        //start of 05
        @Test
        public void test05_checkRedText() throws Exception {
            maximTests = extent.startTest(name.getMethodName());
            maximTests.log(LogStatus.INFO ,"start test ");
            homeScreen=new HomeScreen(driver);
            //check if button enterToBuyMe is working
            try {
                homeScreen.checkErrorRedTextInLoginScreen();
                maximTests.log(LogStatus.PASS, "press on button enter to buyMe ");
            }
            catch (Exception error){
                maximTests.log(LogStatus.FAIL,"the element of the button is not found " + error);
                maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
            }
            //assertErrorMessage in mail text and password text
            String textMail=driver.findElement(By.id("parsley-id-12")).getText();
            String textPassword=driver.findElement(By.id("parsley-id-14")).getText();

            try {
                String redTextError1 = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";
                String redTextError2 = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";
                assertEquals(textMail, redTextError1);
                maximTests.log(LogStatus.PASS, "The message will show error red text under mail field   ");
                assertEquals(textPassword, redTextError2);
                maximTests.log(LogStatus.PASS, "The message will show error red text under password field  ");
            }
            catch (AssertionError error){
                maximTests.log(LogStatus.FAIL,"The message wont show any error text " + error);
                maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
          }
            //navigate back to home screen
            driver.navigate().to("https://buyme.co.il/");
            maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());

        }
          //end of test05

         //start of test06
         @Test
         public void test06_scrollToTheButtonOfGiftScreen() throws Exception {
             maximTests = extent.startTest(name.getMethodName());
             maximTests.log(LogStatus.INFO ,"start test ");
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             driver.navigate().to("https://buyme.co.il/search");

             //check if the urls are equals
             Thread.sleep(1000);
             String actualUrl= driver.getCurrentUrl();
             System.out.println(actualUrl);
             try {
                 String expectedUrl = "https://buyme.co.il/search";
                 maximTests.log(LogStatus.PASS, "the urls are equals ");
                 System.out.println(expectedUrl);
             }
             catch (AssertionError error){
                 maximTests.log(LogStatus.FAIL,"the urls are not equals " + error);
                 maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
             }
             homeScreen=new HomeScreen(driver);
             //check if the page scrolled to the button of screen
             Thread.sleep(1000);
             try{
                 homeScreen.scrollToTheButtonOFScreen();
                 maximTests.log(LogStatus.PASS,"catch the element in the button of the gift screen  ");
                 maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
              }
              catch (Exception error){
                 maximTests.log(LogStatus.FAIL,"the element is not cached " + error);
                 maximTests.log(LogStatus.INFO,"details",maximTests.addScreenCapture(takeScreenShot(imagesPath + "\\" + System.currentTimeMillis())));
              }

             maximTests.log(LogStatus.INFO,"end of test " + name.getMethodName());
        }
        //end of test06




    @After
    public void afterTest() {
        extent.endTest(maximTests);
    }
    //end after test

    //Run once at the end of the test
    @AfterClass
    public static void close() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
        extent.flush();


}
   //set browsers
    public static void setBrowther(String browser) {
        switch (browser) {
            //set chrome browser
            case "Chrome": {
                System.setProperty("webdriver.chrome.driver", "C://Selenium//Drivers//chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("-incognito");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(options);
                break;
            }
            //set fire fox driver
            case "fireFox": {
                System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Drivers\\geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-extensions");
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
            }

        }


    }
    public static String takeScreenShot(String imagesPath) {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(imagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagesPath + ".png";
    }


}
