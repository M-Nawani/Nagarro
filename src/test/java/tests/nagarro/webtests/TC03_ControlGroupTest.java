package tests.nagarro.webtests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;
import webAssignment.nagarro.controlGroup.ControlGroup;

import java.io.IOException;
import java.util.logging.Logger;

public class TC03_ControlGroupTest extends ControlGroup {

    public static final Logger log = Logger.getLogger(TC03_ControlGroupTest.class.getName());
    public static ExtentTest test;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        init();
    }

    @Test
    public void verifyControlGroup() throws InterruptedException {
        test = ExtentTestManager.startTest("Verify control group", "To Verify control group");
        test.log(Status.INFO, "Control group page");

        ControlGroup controlGroup  = new ControlGroup();
        Assert.assertEquals("Demos",controlGroup.verifyHomePage());
        test.log(Status.INFO, "HomePage launched");

        Assert.assertEquals("Controlgroup",controlGroup.clickControlGroup());
        test.log(Status.INFO, "Control Group Clicked");

        controlGroup.performControlGroupActions();
    }

    @AfterMethod
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
