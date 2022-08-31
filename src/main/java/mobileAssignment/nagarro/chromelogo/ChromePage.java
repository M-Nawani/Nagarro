package mobileAssignment.nagarro.chromelogo;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import setup.nagarro.base.TestBase;

public class ChromePage extends TestBase {

    @FindBy(id = "android:id/button1")
    WebElement popoupButton;

    @FindBy(id = "android:id/title")
    public WebElement homepageTitle;

    @FindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
    public WebElement chromeLogoButton;

    @FindBy(xpath = "//android.view.View[@package='io.selendroid.testapp']")
    public WebElement verifyFormPage;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='name_input']")
    public WebElement nameInput;

    @FindBy(xpath = "//android.widget.Spinner[@package='io.selendroid.testapp'][@scrollable='false']")
    public WebElement selectCarButton;

    @FindBy(xpath = "//android.view.View[@index='0']")
    public WebElement verifyFormPageHeading;

    // Verify added name
    @FindBy(xpath = "//android.view.View[@index='3']")
    public WebElement verifyAddedName;

    // Verify Added car
    @FindBy(xpath = "//android.view.View[@index='5']")
    public WebElement verifyAddedCar;

    @FindBy(xpath = "//android.view.View[@index='9']")
    public WebElement StartAgainButton;

    @SuppressWarnings("unchecked")
    public ChromePage(@SuppressWarnings("rawtypes") AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(androidDriver, this);
    }

    public void verifyChromePage() throws InterruptedException {
        if (popoupButton.isDisplayed()) {
            popoupButton.click();
        }
        Assert.assertEquals("selendroid-test-app",homepageTitle.getText());
        explicitWait(androidDriver, chromeLogoButton);
        chromeLogoButton.click();
        Thread.sleep(60);
        explicitWait(androidDriver, androidDriver.findElementById("io.selendroid.testapp:id/tableRowWebview"));
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys("Monika");
        selectCarButton.click();
    }

}
