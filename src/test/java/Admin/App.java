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
	
	
	// Driver declaration
	WebDriver driver;
	//login page Object creation 
	LoginPage lp;
	// reports object creation
	static ExtentReports reports;
	static ExtentSparkReporter sparkreport;
	
	
	//setup
	@Before
	public void setup() {
		//driver declaration
		driver = new EdgeDriver();
		//browser maximization
		driver.manage().window().maximize();
		//object initialization
		lp = new LoginPage(driver);
		
	}
	
	// Onetime setup
	@BeforeClass
	public static void ReportGeneration() {
		//reports declaration
		reports = new ExtentReports();
		//declaring spark report 
		sparkreport = new ExtentSparkReporter("C:\\Users\\Sarath\\eclipse-workspace\\JUnit_Framework\\Reports\\report.html");
		//attaching the spark report to report
		reports.attachReporter(sparkreport);
	}
	
	//onetime teardown
	@AfterClass
	public static void flush() {
		// reports flush
		reports.flush();
	}
	
	//method for reporting pass testcase
	public void testcaseIfPassed(String title, String desctiption) {
		ExtentTest test = this.reports.createTest(title);
		test.pass(desctiption);
	}
	
	//method for reporting fail testcase
	public void testcaseIffailed(String title, String description, Exception ex) {
		ExtentTest test = this.reports.createTest(title);
		test.fail(description);
		
		//screenshot object creation
		TakesScreenshot srcshot = ((TakesScreenshot)driver);
		
		//attaching the screenshot to report
		test.addScreenCaptureFromBase64String(srcshot.getScreenshotAs(OutputType.BASE64), title);
	}
	

	//Testcase
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
