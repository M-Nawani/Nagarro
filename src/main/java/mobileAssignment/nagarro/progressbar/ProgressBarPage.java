package mobileAssignment.nagarro.progressbar;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.nagarro.base.TestBase;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class ProgressBarPage extends TestBase {

    @FindBy(id = "android:id/button1")
    WebElement popoupButton;

    @FindBy(id = "io.selendroid.testapp:id/inputPassword")
    public WebElement verifyRegisterPassword;

    @FindBy(id = "io.selendroid.testapp:id/inputName")
    public WebElement verifyRegisterInputName;

    @FindBy(id = "android:id/text1")
    public WebElement verifyRegisterProgrammingLanguage;

    @FindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
    public WebElement progressBarButton;

    @FindBy(id = "io.selendroid.testapp:id/inputUsername")
    public WebElement verifyRegisterUserName;

    @FindBy(id = "io.selendroid.testapp:id/inputEmail")
    public WebElement verifyRegisterEmail;

    @FindBy(id = "io.selendroid.testapp:id/input_adds")
    public WebElement verifyRegisterTCCheckBox;

    @FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    public WebElement verifyRegisterButton;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
    public WebElement verifyRegistrationPage;


    public ProgressBarPage(@SuppressWarnings("rawtypes") AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(androidDriver, this);
    }

    public void verifyProgressBar(){

        if (popoupButton.isDisplayed()) {
            popoupButton.click();
        }

        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        progressBarButton.click();
        explicitWaitTillInvisibility(androidDriver, androidDriver.findElementById("android:id/progress"));

    }

    public String verifyUserRegPage(){
        return verifyRegistrationPage.getText();
    }

    public void verifyUserRegPageEelements() throws InterruptedException {
        Thread.sleep(3000);
        androidDriver.hideKeyboard();
        checkRegistrationForm(verifyRegisterUserName);
        checkRegistrationForm(verifyRegisterEmail);
        checkRegistrationForm(verifyRegisterPassword);
        checkRegistrationForm(verifyRegisterInputName);
        checkRegistrationForm(verifyRegisterProgrammingLanguage);
        checkRegistrationForm(verifyRegisterTCCheckBox);
        checkRegistrationForm(verifyRegisterButton);
    }

    public void checkRegistrationForm(WebElement element) {

        if (isClickable(element, androidDriver)) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }

    }
}
