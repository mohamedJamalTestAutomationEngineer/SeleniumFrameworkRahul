package testingComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingObject;

	public WebDriver initalizeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		//String browserName = "chrome";
	
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440, 900));  // optional to avoid potential defects that element not visible
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		return driver;
	}

	public List<HashMap<String, String>> getDataFromJSON(String Filepath) throws IOException {

		// store the file content into string
		String jsonContent = FileUtils.readFileToString(new File(Filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		// receice the sting into list of HashMap
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String takeScreenshotMethod(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	
	/*
	@Before
	public LandingPage fakeMethodforCUCumber() throws IOException {
		driver = initalizeDriver();
		landingObject = new LandingPage(driver);
		landingObject.GoToMainPage();
		return landingObject;
	}
	*/
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage lanuchApplication() throws IOException {
		driver = initalizeDriver();
		landingObject = new LandingPage(driver);
		landingObject.GoToMainPage();
		return landingObject;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
