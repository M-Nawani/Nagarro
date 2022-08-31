package setup.nagarro.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static final String reportName = "AutomationReport" + ".html";
    private static final String fileSeparator = System.getProperty("file.separator");
    private static final String reportPath = System.getProperty("user.dir") + fileSeparator + "TestReport";
    private static final String reportLocation = reportPath + fileSeparator + reportName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance(){
        String fileName = createReportPath(reportPath);
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(fileName);
        extentHtmlReporter.config().setDocumentTitle(reportName);
        extentHtmlReporter.config().setReportName(reportName);
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extent  = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);

        //System information in report
        extent.setSystemInfo("Executed on Environment: ", ReadPropertiesFile.getProperty("environment"));
        extent.setSystemInfo("Executed on Browser: ", ReadPropertiesFile.getProperty("browser"));
        extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));

        return extent;
    }

    //Create the report path if it does not exist
    private static String createReportPath (String reportPath) {
        File testDirectory = new File(reportPath);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + reportPath + " is created!" );
                return reportLocation;
            } else {
                System.out.println("Failed to create directory: " + reportPath);
                return (System.getProperty("user.dir"));
            }
        } else {
            System.out.println("Directory already exists: " + reportPath);
        }
        return reportLocation;
    }
}
