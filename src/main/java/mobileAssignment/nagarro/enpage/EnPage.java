package mobileAssignment.nagarro.enpage;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.nagarro.base.TestBase;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class EnPage extends TestBase {

    @FindBy(id = "continue_button")
    WebElement continueButton;

    @FindBy(id = "android:id/button1")
    WebElement popoupButton;

    @FindBy(id = "android:id/title")
    WebElement homepageTitle;

    @FindBy(id = "io.selendroid.testapp:id/buttonTest")
    WebElement enButton;

    @FindBy(id = "android:id/button2")
    WebElement endActivityNoButton;


    public EnPage(@SuppressWarnings("rawtypes") AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(androidDriver, this);
    }

    public String verifyHomePage() {
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        if (popoupButton.isDisplayed()) {
            popoupButton.click();
        }
        return homepageTitle.getText();
    }

    public boolean checkEnButtonisDisplayed() {
        return enButton.isDisplayed();
    }

    public void clickEnButton(){
        enButton.click();
    }

    public void clickEndActivityNoButton(){
        endActivityNoButton.click();
        assertEquals("selendroid-test-app", homepageTitle.getText());


    }
}


