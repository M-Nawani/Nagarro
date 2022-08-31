package tests.nagarro.webtests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;
import webAssignment.nagarro.selectable.Selectable;

import java.io.IOException;
import java.util.logging.Logger;

public class TC02_SelectableTest extends Selectable {

    public static final Logger log = Logger.getLogger(TC02_SelectableTest.class.getName());
    public static ExtentTest test;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        init();
    }

    @Test
    public void verifyItemSelection(){
        test = ExtentTestManager.startTest("Verify selection of multiple items", "To Verify selection of multiple items");
        test.log(Status.INFO, "Select multiple items");
        Selectable selectable  = new Selectable();
        Assert.assertEquals("Demos",selectable.verifyHomePage());
        test.log(Status.INFO, "HomePage launched");

        Assert.assertEquals("Selectable", selectable.clickSelectable());
        test.log(Status.INFO, "Selectable clicked successfully");

        selectable.selectItems();
        test.log(Status.INFO, "Multiple items selected successfully");
        log.info("Multiple items selected successfully");

    }

    @AfterMethod
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}

