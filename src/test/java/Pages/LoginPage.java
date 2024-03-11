package Pages;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver dr) {
		driver = dr;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how = How.CSS, using ="#Email")
	public WebElement username;
	
	@FindBy(how = How.CSS, using ="#Passwor")
	public WebElement password;
	
	@FindBy(how = How.XPATH, using ="//button[text()='Log in']")
	public WebElement login_btn;
	
	@FindBy(how = How.LINK_TEXT, using ="Logout")
	public WebElement sign_out_btn;
	
	
	public void login() {
		driver.navigate().to("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		username.clear();
		username.sendKeys("admin@yourstore.com");
		password.clear();
		password.sendKeys("admin");
		login_btn.click();
		Assert.assertTrue(sign_out_btn.isDisplayed());
	}
	
	
	
	
	

}
