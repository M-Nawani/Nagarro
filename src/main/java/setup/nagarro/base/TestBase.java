package setup.nagarro.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.nagarro.utilities.ReadPropertiesFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static final Logger log = Logger.getLogger(TestBase.class.getName());
    String browser = ReadPropertiesFile.getProperty("browser");
    public static WebDriver driver;
    public AndroidDriver<AndroidElement> androidDriver;

    public void init() throws InterruptedException, IOException {
        selectBrowser(browser);
        getUrl(ReadPropertiesFile.getProperty("applicationUrl"));
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    public void getUrl(String url) {
        log.info(url);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

    }

    public void selectBrowser(String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Creating object of " + browser);
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            ChromeDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            TimeUnit.SECONDS.sleep(1);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("headless")) {
           System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            ChromeDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--window-size=1200,1200", "--ignore-certificate-errors", "--silent");
            driver = new ChromeDriver(options);

        }
    }

    public void capabilities() throws IOException {

        File file = new File(ReadPropertiesFile.getProperty("SelendroidApp"));
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, ReadPropertiesFile.getProperty("deviceName"));
        cap.setCapability(MobileCapabilityType.VERSION, "v1.22.3");
        cap.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("fullReset", "true");

        androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }

    public void explicitWait(WebDriver driver, WebElement xpathValue) {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(xpathValue));
    }

    public void scrollIntoView(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void explicitWaitTillInvisibility(WebDriver driver, WebElement xpathValue) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(xpathValue));

    }

    public static boolean isClickable(WebElement element, AndroidDriver<AndroidElement> driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
