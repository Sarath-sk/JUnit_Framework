package Admin;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.LoginPage;

public class App {
	
	WebDriver driver;
	LoginPage lp;
	static ExtentReports reports;
	static ExtentSparkReporter sparkreport;
	
	
	@Before
	public void setup() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		lp = new LoginPage(driver);
		
	}
	
	
	@BeforeClass
	public static void ReportGeneration() {
		reports = new ExtentReports();
		sparkreport = new ExtentSparkReporter("C:\\Users\\Sarath\\eclipse-workspace\\JUnit_Framework\\Reports\\report.html");
		reports.attachReporter(sparkreport);
	}
	
	@AfterClass
	public static void flush() {
		reports.flush();
	}
	
	public void testcaseIfPassed(String title, String desctiption) {
		ExtentTest test = this.reports.createTest(title);
		test.pass(desctiption);
	}
	
	public void testcaseIffailed(String title, String description, Exception ex) {
		ExtentTest test = this.reports.createTest(title);
		test.fail(description);
		
		TakesScreenshot srcshot = ((TakesScreenshot)driver);
		test.addScreenCaptureFromBase64String(srcshot.getScreenshotAs(OutputType.BASE64), title);
	}
	

	
	@Test
	public void ValidateLogin() {
		try {
			lp.login();
			this.testcaseIfPassed("ValidateLogin", "Login Successfull");
		}catch(Exception ex) {
			this.testcaseIffailed("ValidateLogin", "Login is not successful",ex);
			throw ex;
			
		}finally{
			driver.close();
		}
	}
	
	

}
