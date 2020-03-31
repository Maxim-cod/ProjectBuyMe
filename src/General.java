import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

public class General {
    public static WebDriver driver;
    String imagesPath = ("C:\\Users\\MAXIM\\IdeaProjects\\byMe\\ScreenShots");

    //xml file path
    public static String readFromFile(String KeyData) throws Exception {
        File xmlFile = new File("C:\\Users\\MAXIM\\IdeaProjects\\byMe\\configFile.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName(KeyData).item(0).getTextContent();
    }
    //screenshot function
//    public static String takeScreenShot(String imagesPath) {
//
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        File destinationFile = new File(imagesPath + ".png");
//        try {
//            FileUtils.copyFile(screenShotFile, destinationFile);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return imagesPath + ".png";
//    }

   //function that return radio value
    public static void selectRadioButton(WebElement radio_label) {
        boolean selected_value=radio_label.isSelected();
        if(!selected_value);
        radio_label.click();
    }

   //function that return combo value
    public  static void selectOptionsByValue(String value, WebElement element) {
        Select select = new Select(element);
        select.selectByValue(value);


    }

    //uploading file function
    public static void uploadPicture(WebElement Element,String picturePath){
        Element.sendKeys("D:\\games\\jpg\\maxresdefault.jpg");


    }



    }



