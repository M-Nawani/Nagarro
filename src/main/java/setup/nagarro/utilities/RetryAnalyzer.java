package setup.nagarro.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;

    //Count for which the failed method will be re-tried
    int retryCount = 2;
    @Override
    public boolean retry(ITestResult result) {
        while(count <retryCount){
            count ++;
            return true;
        }
        return false;
    }


}
