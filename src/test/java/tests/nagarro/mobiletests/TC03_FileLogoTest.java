package tests.nagarro.mobiletests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import mobileAssignment.nagarro.filelogo.FileLogo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;

import java.io.IOException;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class TC03_FileLogoTest extends TestBase {

    public static final Logger log = Logger.getLogger(TC03_FileLogoTest.class.getName());

    public static ExtentTest test;

    @BeforeMethod
    public void init() throws IOException {
        capabilities();
    }

    // @Parameters({"name", "password", "emailId"})
    @Test
    public void userRegPage() throws InterruptedException {
        test = ExtentTestManager.startTest("Verify user reg page", " Verify user reg page");
        test.log(Status.INFO, " Verify user reg page");
        FileLogo fileLogo = new FileLogo(androidDriver);

        Assert.assertEquals("Welcome to register a new User", fileLogo.verifyUserRegistrationPage());
        test.log(Status.INFO, "Successfully verified registration page");

        fileLogo.enterUserDetails("Monika", "Guest@123", "test@tester.com");
        Assert.assertEquals("Mr. Burns", fileLogo.verifydefaultName());
        test.log(Status.INFO, "Successfully verified default name");

        Assert.assertEquals("Ruby", fileLogo.verifydefaultLanguage());
        test.log(Status.INFO, "Successfully verified default language");

        fileLogo.verifytermscheckbox();
        Assert.assertEquals("Monika",fileLogo.verifyUserName());
        Assert.assertEquals("Guest@123", fileLogo.verifyPassword());
        Assert.assertEquals("test@tester.com", fileLogo.verifyEmail());

        test.log(Status.INFO, "Successfully verified entered user info");

        fileLogo.verifyTerms();
        assertEquals("EN Button",fileLogo.verifyHomepage());
        test.log(Status.INFO, "Successfully verified user is back at homepage");
    }

    @AfterMethod
    public void endTest() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }


}
