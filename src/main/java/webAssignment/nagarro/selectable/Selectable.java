package webAssignment.nagarro.selectable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import setup.nagarro.base.TestBase;

public class Selectable extends TestBase {

    @FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
    WebElement verifyHomePage;

    @FindBy(xpath = "//a[contains(text(),'Selectable')]")
    private WebElement selectableButton;

    @FindBy (xpath = "//h1[contains(text(),'Selectable')]")
    private WebElement verifySelectablePage;

    @FindBy(xpath = "//iframe[@class='demo-frame']")
    private WebElement demoFrame;

    @FindBy(id = "selectable")
    WebElement itemView;

    @FindBy(xpath = "//ol[@id='selectable']/li[1]")
    private WebElement item1;

    @FindBy(xpath = "//ol[@id='selectable']/li[3]")
    private WebElement item3;

    @FindBy(xpath = "//ol[@id='selectable']/li[7]")
    private WebElement item7;

    public Selectable(){
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePage(){
        explicitWait(driver, verifyHomePage);
        return verifyHomePage.getText();
    }

    public String clickSelectable(){
        selectableButton.click();
        return verifySelectablePage.getText();
    }

    public void selectItems(){

        driver.switchTo().frame(demoFrame);
        scrollIntoView(driver,itemView);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).perform();

        item1.click();
        item3.click();
        item7.click();

        actions.keyUp(Keys.CONTROL).perform();


    }


}
