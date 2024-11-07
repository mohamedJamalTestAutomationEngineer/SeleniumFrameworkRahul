package testingComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportsNG;

public class ListenersNG extends BaseTest implements ITestListener {
	
	ExtentReports extentReportsObj = ExtentReportsNG.getReports();
	ExtentTest extentTests; 
	// thread are used in case of parallel testing to avoid override testing results 
	ThreadLocal<ExtentTest> extentestLocalThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentTests = extentReportsObj.createTest(result.getMethod().getMethodName());
		extentestLocalThread.set(extentTests);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentestLocalThread.get().log(Status.PASS, "Test is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentestLocalThread.get().fail(result.getThrowable());
		String filePath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			filePath = takeScreenshotMethod(result.getMethod().getMethodName(),driver);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentestLocalThread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReportsObj.flush();
	}
	

}
