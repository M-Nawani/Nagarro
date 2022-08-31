package tests.nagarro.mobiletests;

import com.aventstack.extentreports.ExtentTest;
import mobileAssignment.nagarro.chromelogo.ChromePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;

import java.io.IOException;
import java.util.logging.Logger;

public class TC02_ChromePageTest extends TestBase {

    public static final Logger log = Logger.getLogger(TC01_ENPageTest.class.getName());

    public static ExtentTest test;

    @BeforeMethod
    public void init() throws IOException {
        capabilities();
    }

    @Test
    public void everifyChromePage() throws InterruptedException {
        ChromePage chromePage = new ChromePage(androidDriver);
        chromePage.verifyChromePage();
     }

    @AfterMethod
    public void endTest() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
