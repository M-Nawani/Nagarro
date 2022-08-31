package tests.nagarro.mobiletests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mobileAssignment.nagarro.homepage.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;

import java.io.IOException;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class TC_05HomePageTest extends TestBase {

    public static final Logger log = Logger.getLogger(TC_05HomePageTest.class.getName());

    public static ExtentTest test;
    public static HomePage homePage;

    @BeforeMethod
    public void init() throws IOException {
        capabilities();
    }

    @Test(priority = 1)
    public void verifytoastisDisplayed() throws IOException {
        test = ExtentTestManager.startTest("Verify toast ", "Verify toast");
        test.log(Status.INFO, "Verify toast");
        homePage = new HomePage(androidDriver);
        Assert.assertEquals("Hello selendroid toast!", homePage.verifyDisplaysAToast());
        test.log(Status.INFO, "Successfully verified toast text");
    }

    @Test(priority = 2)
    public void verifyPopupisDisplayed(){
        homePage = new HomePage(androidDriver);
        homePage.verifyPopupDisplay();
        test.log(Status.INFO, "Successfully verified dismiss popup");
    }

    @Test(priority = 3)
    public void verifyUnhandledException(){
        homePage = new HomePage(androidDriver);
        homePage.verifyUnhandledException();
        test.log(Status.INFO, "Successfully verified unhandled exception");
    }

    @Test(priority = 4)
    public void verifyUnhandledExceptionText(){
        homePage = new HomePage(androidDriver);
        homePage.verifyUnhandledExceptionText();
        test.log(Status.INFO, "Successfully verified unhandled exception text");
    }

    @AfterMethod
    public void endTest() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
