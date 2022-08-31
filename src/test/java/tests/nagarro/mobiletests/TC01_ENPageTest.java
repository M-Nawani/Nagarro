package tests.nagarro.mobiletests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mobileAssignment.nagarro.enpage.EnPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;

import java.io.IOException;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC01_ENPageTest extends TestBase {

    public static final Logger log = Logger.getLogger(TC01_ENPageTest.class.getName());

    public static ExtentTest test;

    @BeforeMethod
    public void init() throws IOException {
        capabilities();
    }

    @Test(priority = 1)
    public void enButtonVerify() {

        test = ExtentTestManager.startTest("Mobile - verify selandroid homepage", "verify selandroid homepage");
        test.log(Status.INFO, "verify selandroid homepage");

        EnPage homepage = new EnPage(androidDriver);
        test.log(Status.INFO, "Successfully verified selandroid homepage");
        assertEquals("selendroid-test-app", homepage.verifyHomePage());

        test.log(Status.INFO, "Successfully verified if EN button is displayed");
        assertTrue(homepage.checkEnButtonisDisplayed());

        homepage.clickEnButton();

        test.log(Status.INFO, "End activity button clicked");
        homepage.clickEndActivityNoButton();
    }

    @AfterMethod
    public void endTest() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
