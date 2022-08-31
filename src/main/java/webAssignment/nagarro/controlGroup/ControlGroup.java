package webAssignment.nagarro.controlGroup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import setup.nagarro.base.TestBase;

public class ControlGroup extends TestBase {

    @FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
    private WebElement verifyHomePage;

    @FindBy(xpath = "//a[contains(text(),'Controlgroup')]")
    private WebElement controlGroupButton;

    @FindBy(xpath = "//h1[contains(text(),'Controlgroup')]")
    WebElement verifyControlgroupPage;

    @FindBy(xpath = "//iframe[@class='demo-frame']")
    private WebElement demoFrame;

    @FindBy(id = "car-type-button")
    WebElement compactCarDropDownButton;

    @FindBy(id = "ui-id-4")
    private WebElement horizontalSUVCarType;

    @FindBy(xpath = "//label[@for='insurance'][@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item']")
    private WebElement horizontalInsuranceCheckbox;

    @FindBy(xpath = "//label[@for='insurance-v']/span[1]")
    private WebElement verticalInsuranceCheckbox;

    @FindBy(id = "book")
    private WebElement verticalBookNowButton;

    @FindBy(id = "vertical-spinner")
    private WebElement verticalSpinner;

    @FindBy(xpath = "//label[@for='transmission-standard-v']/span[1]")
    private WebElement verticalStandardRadioButton;

    @FindBy(id = "ui-id-8-button")
    private WebElement verticalCompactCarDropDownButton;

    @FindBy(id = "ui-id-14")
    private WebElement verticalTruckCarType;

    @FindBy(id = "horizontal-spinner")
    private WebElement horizontalNumberofCars;

    @FindBy(xpath = "//label[@for='transmission-automatic']/span[1]")
    private WebElement horizontalAutomatic;

    public ControlGroup(){
        PageFactory.initElements(driver, this);
    }

    public String verifyHomePage(){
        explicitWait(driver, verifyHomePage);
        return verifyHomePage.getText();
    }

    public String clickControlGroup(){
        controlGroupButton.click();
        return verifyControlgroupPage.getText();
    }

    public void performControlGroupActions() throws InterruptedException {
        driver.switchTo().frame(demoFrame);
        scrollIntoView(driver,compactCarDropDownButton);
        compactCarDropDownButton.click();
        horizontalSUVCarType.click();

        horizontalAutomatic.click();
        horizontalInsuranceCheckbox.click();
        horizontalNumberofCars.sendKeys("2");

        verticalCompactCarDropDownButton.click();
        verticalTruckCarType.click();
        verticalInsuranceCheckbox.click();
        verticalStandardRadioButton.click();
        verticalSpinner.sendKeys("1");
        verticalBookNowButton.click();

    }








}
