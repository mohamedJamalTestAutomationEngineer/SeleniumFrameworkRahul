package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports getReports() {
		
		
		String filePath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(filePath);
		
		sparkReport.config().setDocumentTitle("Document Title");
		sparkReport.config().setReportName("Report Name");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("TesterName" , "Jamal");
		extentReport.setSystemInfo("OS", "Windows 10");
		extentReport.setSystemInfo("Environment", "ChromeDriver");
		
		return extentReport;
	}

}
