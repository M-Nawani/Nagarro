package setup.nagarro.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import setup.nagarro.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener, IAnnotationTransformer {

//    private static final ExtentReports extent = ExtentManager.createInstance();
//    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if(result.getStatus()==ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case " + methodName + " PASSED";
            Markup mark = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            ExtentTestManager.getTest().log(Status.PASS, mark);

        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case " + methodName + " FAILED";
            Markup mark = MarkupHelper.createLabel(logText, ExtentColor.RED);
            ExtentTestManager.getTest().log(Status.FAIL, mark);
            ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date date = new Date();
            String actualDate = format.format(date);
            String screenshotPath = System.getProperty("Screenshots_"+actualDate+".jpeg");
            File srcFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
            File destFile =new File(screenshotPath);
            try {
                FileUtils.copyFile(srcFile,destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if(result.getStatus()==ITestResult.SKIP){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case " +methodName + " SKIPPED";
            Markup mark = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
            ExtentTestManager.getTest().log(Status.SKIP, mark);
        }
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite " + context.getName() + " has started");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite " + context.getName() + " has ended");
        ExtentManager.getInstance().flush();
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
