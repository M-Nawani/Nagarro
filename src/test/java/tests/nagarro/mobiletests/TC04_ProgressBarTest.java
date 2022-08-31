package tests.nagarro.mobiletests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mobileAssignment.nagarro.progressbar.ProgressBarPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;

import java.io.IOException;
import java.util.logging.Logger;

public class TC04_ProgressBarTest extends TestBase {

    public static final Logger log = Logger.getLogger(TC04_ProgressBarTest.class.getName());

    public static ExtentTest test;

    @BeforeMethod
    public void init() throws IOException {
        capabilities();
    }

    @Test
    public void verifyProgress() throws InterruptedException {
        test = ExtentTestManager.startTest("Verify progress bar", "Verify progress bar");
        test.log(Status.INFO, "Verify progress bar");
        ProgressBarPage progress = new ProgressBarPage(androidDriver);
        progress.verifyProgressBar();
        Assert.assertEquals("Welcome to register a new User", progress.verifyUserRegPage());
        test.log(Status.INFO, "Successfully verified progress bar");

        progress.verifyUserRegPageEelements();
    }

    @AfterMethod
    public void endTest() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }


}
