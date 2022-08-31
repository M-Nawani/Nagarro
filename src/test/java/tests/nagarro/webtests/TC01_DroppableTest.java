package tests.nagarro.webtests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.nagarro.base.TestBase;
import setup.nagarro.utilities.ExtentTestManager;
import webAssignment.nagarro.droppable.Droppable;

import java.io.IOException;
import java.util.logging.Logger;

public class TC01_DroppableTest extends Droppable {
    public static final Logger log = Logger.getLogger(TC01_DroppableTest.class.getName());
    public static ExtentTest test;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        init();
    }

    @Test
    public void verifyDragandDrop(){
        Droppable drop = new Droppable();
        test = ExtentTestManager.startTest("Verify drag and drop", "To verify drag and drop");
        test.log(Status.INFO, "Drag and drop from source to destination");

        Assert.assertEquals("Demos",drop.verifyHomePage());
        test.log(Status.INFO, "HomePage launched");

        Assert.assertEquals("Droppable",drop.clickDroppable());
        test.log(Status.INFO, "Droppable clicked successfully");

        Assert.assertEquals("Dropped!", drop.dragAndDropAction());
        log.info("Drag and drop successful");
        test.log(Status.INFO, "Drag and drop successful");
    }

    @AfterMethod
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
