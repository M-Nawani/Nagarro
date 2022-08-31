package mobileAssignment.nagarro.homepage;

import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.nagarro.base.TestBase;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static org.testng.Assert.assertEquals;

public class HomePage extends TestBase {

    @FindBy(id = "android:id/button1")
    WebElement popoupButton;

    @FindBy(id = "io.selendroid.testapp:id/showToastButton")
    public WebElement showToastButton;

    @FindBy(xpath = "/hierarchy/android.widget.Toast")
    public WebElement verifyToastText;

    @FindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
    public WebElement showPopupWindowButton;

    @FindBy(id = "io.selendroid.testapp:id/exceptionTestButton")
    public WebElement exceptionTestButton;

    @FindBy(id = "io.selendroid.testapp:id/exceptionTestField")
    public WebElement exceptionTestField;

    @SuppressWarnings("unchecked")
    public HomePage(@SuppressWarnings("rawtypes") AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(androidDriver, this);

    }

    public String verifyDisplaysAToast() throws IOException {
        handlePopUp();
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        showToastButton.click();

        WebDriverWait waitForToast = new WebDriverWait(androidDriver, 25);
        waitForToast.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
        return verifyToastText.getText();
    }

    public void verifyPopupDisplay() {
        handlePopUp();
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        showPopupWindowButton.click();
        tapCoordinates(550, 1063);
    }

    public void verifyUnhandledException(){
        handlePopUp();
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        try {
            exceptionTestButton.click();
        } catch (Exception e) {
            log.info("Error log - " + e.toString());
        }
    }

    public void verifyUnhandledExceptionText(){
        handlePopUp();
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        try {
            exceptionTestField.sendKeys("test");

        } catch (Exception e) {
            log.info("Error log - " + e.toString());
        }

    }

    private void tapCoordinates(int x, int y) {
        new TouchAction(androidDriver).tap(point(x, y)).waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }

    private void handlePopUp(){
        if (popoupButton.isDisplayed()) {
            popoupButton.click();
        }
    }
}
