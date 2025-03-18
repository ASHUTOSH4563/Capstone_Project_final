package stepdefinitions;
import hooks.Hooks;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zerobank.pages.LoginPage;

public class LoginStepDefinition {
	
	static ExtentReports extent;
	static ExtentTest test;
	static Properties properties;

	static {
		try {
			
			properties = new Properties();
			FileInputStream fis = new FileInputStream("src/test/java/Config/extent.properties");
			properties.load(fis);

			
			String reportPath = properties.getProperty("extent.reporter.spark.out");

			
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.loadXMLConfig("src/test/java/Config/extent-config.xml");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			
			extent.setSystemInfo("OS", properties.getProperty("systeminfo.os"));
			extent.setSystemInfo("User", properties.getProperty("systeminfo.user"));
			extent.setSystemInfo("Build", properties.getProperty("systeminfo.build"));
			extent.setSystemInfo("App Name", properties.getProperty("systeminfo.AppName"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    private WebDriver d=Hooks.getDriver();
    LoginPage loginPage;

   
	@Given("User opens the Zero Bank home page")
    public void user_opens_the_zero_bank_home_page() {
		
		    d.get("http://zero.webappsecurity.com/");
		
        loginPage = new LoginPage(d);
        Assert.assertTrue(d.getTitle().equals("Zero - Personal Banking - Loans - Credit Cards"));
        System.out.println("User opens Zero Bank homepage");
    }

	@When("User navigates to the Signin page")
    public void user_navigates_to_the_signin_page() {
        loginPage.clickSigninButton();
        Assert.assertTrue(d.getTitle().equals("Zero - Log in"));
        System.out.println("User navigates to Signin Page");
    }

    @When("User enters valid username {string}")
    public void user_enters_valid_username(String username) {
        loginPage.enterUsername(username);
        System.out.println("User enters valid username");
    }

    @When("User enters valid password {string}")
    public void user_enters_valid_password(String password) {
        loginPage.enterPassword(password);
        System.out.println("User enters valid password");
    }

    @When("User clicks on the sign-in button")
    public void user_clicks_on_the_sign_in_button() {
        loginPage.clickSignin();
        d.navigate().back();
        System.out.println("User clicks on login button");
    }

	@Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() throws InterruptedException {
        System.out.println("User is successfully logged in");
        
    }

    @When("User enters invalid username {string}")
    public void user_enters_invalid_username(String username) {
        loginPage.enterUsername(username);
        System.out.println("User enters invalid username");
    }

    @When("User enters invalid password {string}")
    public void user_enters_invalid_password(String password) {
        loginPage.enterPassword(password);
        System.out.println("User enters invalid password");
    }

    @When("User clicks on the sign-in button1")
    public void user_clicks_on_the_sign_in_button1() {
        loginPage.clickSignin();
        System.out.println("User clicks on login button");
    }

	@Then("User should see an error message")
    public void user_should_see_an_error_message() {
		loginPage=new LoginPage(d);
         
       loginPage.getErrorMessage();
    }

	@Then("User should remain on the login page")
    public void user_should_remain_on_the_login_page() {
    	Assert.assertTrue(d.getTitle().equals("Zero - Log in"));
        System.out.println("User remains on the same login page");
       
    }

    @When("User leaves the username field blank")
    public void user_leaves_the_username_field_blank() {
        loginPage.enterUsername(" ");
        System.out.println("User leaves username field blank");
    }

    @When("User leaves the password field blank")
    public void user_leaves_the_password_field_blank() {
        loginPage.enterPassword(" ");
        System.out.println("User leaves password field blank");
    }

    @When("User clicks on the sign-in button2")
    public void user_clicks_on_the_sign_in_button2() {
        loginPage.clickSignin();
        System.out.println("User clicks on login button");
    }
}

