package mobileAssignment.nagarro.filelogo;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class FileLogo extends TestBase {
    public static ExtentTest child;

    @FindBy(id = "android:id/button1")
    WebElement popoupButton;

    @FindBy(id = "io.selendroid.testapp:id/buttonTest")
    public WebElement verifyHomePage;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
    public WebElement verifyRegistrationpage;

    @FindBy(id = "io.selendroid.testapp:id/inputUsername")
    public WebElement inputUsername;

    @FindBy(id = "io.selendroid.testapp:id/inputEmail")
    public WebElement inputEmail;

    @FindBy(id = "io.selendroid.testapp:id/inputPassword")
    public WebElement inputPassword;

    @FindBy(id = "io.selendroid.testapp:id/inputName")
    public WebElement defaultInputName;

    @FindBy(id = "android:id/text1")
    public WebElement defaultProgrammingLanguageg;

    @FindBy(id = "io.selendroid.testapp:id/input_adds")
    public WebElement tandCCheckBox;

    @FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    public WebElement registerUserButton;

    @FindBy(id = "io.selendroid.testapp:id/label_name_data")
    public WebElement verifyRegisteredDefaultName;

    @FindBy(id = "io.selendroid.testapp:id/label_username_data")
    public WebElement verifyRegisteredUsername;

    @FindBy(id = "io.selendroid.testapp:id/label_password_data")
    public WebElement verifyRegisteredPassword;

    @FindBy(id = "io.selendroid.testapp:id/label_email_data")
    public WebElement verifyRegisteredEmail;

    @FindBy(id = "io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
    public WebElement verifyRegisteredProgrammingLanguage;

    @FindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
    public WebElement verificationPageRegisterUserButton;

    @SuppressWarnings("unchecked")
    public FileLogo(@SuppressWarnings("rawtypes") AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        PageFactory.initElements(androidDriver, this);
    }

    public String verifyUserRegistrationPage() throws InterruptedException {
        if (popoupButton.isDisplayed()) {
            popoupButton.click();
        }
        androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        androidDriver.findElementByAccessibilityId("startUserRegistrationCD").click();
        Thread.sleep(3000);
        androidDriver.hideKeyboard();
        return verifyRegistrationpage.getText();
    }

    public void enterUserDetails(String name, String password, String emailId){
        inputUsername.sendKeys(name);
        inputPassword.sendKeys(password);
        inputEmail.sendKeys(emailId);

    }

    public String verifyUserName(){
        return verifyRegisteredUsername.getText();
    }

    public String verifyEmail(){
        return verifyRegisteredEmail.getText();
    }

    public String verifyPassword(){
        return verifyRegisteredPassword.getText();
    }

    public String verifydefaultName(){
        return defaultInputName.getText();
    }

    public String verifydefaultLanguage(){
        return defaultProgrammingLanguageg.getText();
    }

    public void verifytermscheckbox(){
        tandCCheckBox.click();
        registerUserButton.click();
    }

    public void verifyTerms() throws InterruptedException {
        verificationPageRegisterUserButton.click();
    }

    public String verifyHomepage(){
        return verifyHomePage.getText();
    }
}
