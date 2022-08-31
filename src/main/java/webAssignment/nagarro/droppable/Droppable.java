package webAssignment.nagarro.droppable;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.nagarro.base.TestBase;


public class Droppable extends TestBase{

    @FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
    private WebElement verifyHomePage;

    @FindBy(xpath = "//a[contains(text(),'Droppable')]")
    private WebElement droppableButton;

    @FindBy(xpath = "//h1[@class='entry-title']")
    WebElement verifyDroppabletitle;

    @FindBy(xpath = "//*[@id=\"content\"]/iframe")
    private WebElement dragdropFrame;

    @FindBy(id = "draggable")
    private WebElement dragSource;

    @FindBy(id = "droppable")
    private WebElement dropDestination;

    @FindBy(xpath = "//p[contains(text(),'Dropped!')]")
    private WebElement dropSuccess;

    public Droppable(){
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePage(){
        explicitWait(driver, verifyHomePage);
        return verifyHomePage.getText();
    }

    public String clickDroppable(){
        droppableButton.click();
        return verifyDroppabletitle.getText();
    }

    public String dragAndDropAction(){

        driver.switchTo().frame(dragdropFrame);
        scrollIntoView(driver, dragSource);
        Actions builder = new Actions(driver);
        builder.dragAndDrop(dragSource, dropDestination).build().perform();
        return dropSuccess.getText();

    }
    

}
