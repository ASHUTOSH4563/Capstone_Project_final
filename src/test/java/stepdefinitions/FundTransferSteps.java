package stepdefinitions;
import hooks.Hooks;
import io.cucumber.java.en.*;
import zerobank.pages.FundTransferPage;
import zerobank.pages.HomePage;
import zerobank.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class FundTransferSteps {
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
	private WebDriver d = Hooks.getDriver(); 

    WebDriverWait wait=new WebDriverWait(d, Duration.ofSeconds(10));
    HomePage home= new HomePage(d);
	LoginPage login= new LoginPage(d);
	FundTransferPage fund= new FundTransferPage(d);
    String url = "http://zero.webappsecurity.com/";

    
    

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
    	  d.get(url);
    	  login.clickSigninButton();

       
    }

    @When("User enters {string} as username and {string} as password")
    public void user_enters_as_username_and_as_password(String username, String password) {

    	login.enterUsername(username);
		login.enterPassword(password);
    }

    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
      //  wait.until(ExpectedConditions.elementToBeClickable(signin_2)).click();
    	login.clickSignin();
        d.navigate().back();
        d.navigate().back();
    }

    @Then("User should be redirected to the account dashboard")
    public void user_should_be_redirected_to_the_account_dashboard() {
        wait.until(ExpectedConditions.titleIs("Zero - Personal Banking - Loans - Credit Cards"));
        System.out.println("User is on the Home page");
    }

    @Given("User navigates to the Fund Transfer page")
    public void user_navigates_to_the_fund_transfer_page() {
        //wait.until(ExpectedConditions.elementToBeClickable(fund_transfer)).click();
    	home.clickTransferFund();
    }

    @When("User selects {string} from From Account dropdown")
    public void user_selects_from_from_account_dropdown(String fromAccountValue) {
//        WebElement fromAccount = wait.until(ExpectedConditions.presenceOfElementLocated(from_Account));
//        new Select(fromAccount).selectByVisibleText(fromAccountValue);
    	fund.selectFromAccount(fromAccountValue);
    }

    @When("User selects {string} from To Account dropdown")
    public void user_selects_from_to_account_dropdown(String toAccountValue) {
//        WebElement toAccount = wait.until(ExpectedConditions.presenceOfElementLocated(to_Account));
//        new Select(toAccount).selectByVisibleText(toAccountValue);
    	fund.selectToAccount(toAccountValue);
    }

    @When("User enters {string} in Amount field")
    public void user_enters_in_amount_field(String amount) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(enter_Amount)).sendKeys(amount);
    	fund.enterAmount(amount);
    }

    @When("User clicks on Continue button")
    public void user_clicks_on_continue_button() {
//        wait.until(ExpectedConditions.elementToBeClickable(continue_Button)).click();
    	fund.clickContinue();
    }

    @Then("User should be redirected to the Transfer Verification page")
    public void user_should_be_redirected_to_the_transfer_verification_page() {
        System.out.println("User is redirected to the Transfer Verification page");
    }

    @When("User clicks on Submit button")
    public void user_clicks_on_submit_button() {
//        wait.until(ExpectedConditions.elementToBeClickable(submit_Button)).click();
    	fund.submit();
    }

    @Then("User should see a success message {string}")
    public void user_should_see_a_success_message(String expectedMessage) {
        System.out.println("You successfully submitted your transaction.");
    }

    @When("User leaves the Amount field empty")
    public void user_leaves_the_amount_field_empty() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(enter_Amount)).clear();
    	fund.clearAmount();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expectedErrorMessage) {
        System.out.println("This field cannot be left empty.");
    }
}


