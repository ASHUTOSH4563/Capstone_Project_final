package stepdefinitions;
import hooks.Hooks;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zerobank.pages.LogOutPage;
import zerobank.pages.LoginPage;

public class LogoutDefinitions {
	
	static ExtentReports extent;
	static ExtentTest test;
	static Properties properties;

	static {
		try {
			// Load properties file
			properties = new Properties();
			FileInputStream fis = new FileInputStream("src/test/java/Config/extent.properties");
			properties.load(fis);

			// Read report path from properties
			String reportPath = properties.getProperty("extent.reporter.spark.out");

			// Setup Extent Reports
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.loadXMLConfig("src/test/java/Config/extent-config.xml");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Adding system info
			extent.setSystemInfo("OS", properties.getProperty("systeminfo.os"));
			extent.setSystemInfo("User", properties.getProperty("systeminfo.user"));
			extent.setSystemInfo("Build", properties.getProperty("systeminfo.build"));
			extent.setSystemInfo("App Name", properties.getProperty("systeminfo.AppName"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private WebDriver cd=Hooks.getDriver();
	WebDriverWait wait;
	LoginPage login;
	LogOutPage logout;
	
	@Given("you in the hompage")
	public void you_in_the_hompage() {	
		
		wait= new WebDriverWait(cd, Duration.ofSeconds(10));
		login= new LoginPage(cd);
		logout= new LogOutPage(cd);
		cd.get("http://zero.webappsecurity.com/index.html");

	}

	@When("Enter the credentials {string} and {string} and login")
	public void enter_the_credentials_and_and_login(String string, String string2) {
		login.clickSigninButton();
		login.enterUsername(string);
		login.enterPassword(string2);
	}

	@When("I log in and redirect to the home page")
	public void i_log_in_and_redirect_to_the_home_page(){
		login.clickSignin();
		cd.navigate().back();
		cd.navigate().back();
	}
	@When("I logout")
	public void I_logout() {
		logout.logout();
	}
	@Then("The user is redirected to the login page")
	public void the_user_is_redirected_to_the_login_page() {
		cd.navigate().back();

	}
	
	@Given("I am on the homepage")
	public void i_am_on_the_homepage() {
		cd= new ChromeDriver();
		login= new LoginPage(cd);
		logout= new LogOutPage(cd);
		cd.get("http://zero.webappsecurity.com/index.html");
		cd.manage().window().maximize();
	}
	@When("I enter {string} and {string} and click login")
	public void i_enter_and_and_click_login(String string, String string2) {
		login.clickSigninButton();
		login.enterUsername(string);
		login.enterPassword(string2);
		login.clickSignin();
		cd.navigate().back();
		cd.navigate().back();
		}
	
	
  @Then("I should be logged in and redirected to the homepage")
  public void i_should_be_logged_in_and_redirected_to_the_homepage(){
      
	  Assert.assertTrue(logout.checkHomepage());
  }

    @When("I click on the logout button")
    public void i_click_on_the_logout_button()  {

		logout.logout();

    }



    @When("I press the back button")
    public void i_press_the_back_button() {
        cd.navigate().back();
    }

    @Then("I should not be able to access the previous page")
    public void i_should_not_be_able_to_access_the_previous_page() {
        Assert.assertTrue(logout.checkHomepage());
        
    }



}
