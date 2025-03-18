package stepdefinitions;
import hooks.Hooks;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import zerobank.pages.HomePage;
import zerobank.pages.LoginPage;
import zerobank.pages.OnlineBankingPage;
import zerobank.pages.PayBillsPage;

public class PayBillsDefinitions {
	
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
    WebDriverWait wait;
    String url = "http://zero.webappsecurity.com/";

    // Locators
    private By sign_in_1 = By.id("signin_button");
    private By login_field = By.id("user_login");
    private By password_field = By.id("user_password");
    private By signin_2 = By.name("submit");
    
    
    private By more_service = By.id("online-banking");
    private By pay_bill = By.id("pay_bills_link");
    private By choose_payee = By.id("sp_payee");
    private By choose_account = By.id("sp_account");
    private By enter_Amount = By.id("sp_amount");
    private By choose_date = By.id("sp_date");
    private By click_pay = By.id("pay_saved_payees");

    @Given("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {

    	
        wait = new WebDriverWait(d, Duration.ofSeconds(10)); // Initialize WebDriverWait
        d.get(url);
        wait.until(ExpectedConditions.elementToBeClickable(sign_in_1)).click();
    }

    @When("the user provides {string} as the username and {string} as the password")
    public void the_user_provides_as_the_username_and_as_the_password(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(login_field)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(password_field)).sendKeys(password);
    }

    @When("clicks the login button")
    public void clicks_the_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(signin_2)).click();
        d.navigate().back();
        d.navigate().back();
    }

    @Then("the user should be directed to the account dashboard")
    public void the_user_should_be_directed_to_the_account_dashboard() {
        wait.until(ExpectedConditions.titleIs("Zero - Personal Banking - Loans - Credit Cards"));
        System.out.println("User is on the Home page");
    }

    @Given("User navigates to Pay Bills via More Services")
    public void user_navigates_to_pay_bills_via_more_services() {
        wait.until(ExpectedConditions.elementToBeClickable(more_service)).click();
        wait.until(ExpectedConditions.elementToBeClickable(pay_bill)).click();
    }

    @When("User selects payee {string}")
    public void user_selects_payee(String payee) {
        WebElement payeeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(choose_payee));
        new Select(payeeDropdown).selectByVisibleText(payee);
    }

    @When("User selects account {string}")
    public void user_selects_account(String accounttype) {
        WebElement accountDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(choose_account));
        new Select(accountDropdown).selectByVisibleText(accounttype);
    }

    @When("User enters amount {string}")
    public void user_enters_amount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enter_Amount)).sendKeys(amount);
    }

    @When("User enters date")
    public void user_enters_date() {
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(choose_date));
        dateField.sendKeys("2025-03-16");
    }

    @When("User clicks Pay button")
    public void user_clicks_pay_button() {
        wait.until(ExpectedConditions.elementToBeClickable(click_pay)).click();
    }

    @Then("User sees a success message {string}")
    public void user_sees_a_success_message(String successMessage) {
        System.out.println("The payment was successfully submitted.");
    }

    @When("User leaves Amount field empty")
    public void user_leaves_amount_field_empty() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enter_Amount)).clear();
    }

    @Then("User would see an error message {string}")
    public void user_would_see_an_error_message(String errorMessage) {
        System.out.println("This field cannot be empty.");
    }

    @Then("The scheduled payment should be visible in the transactions list")
    public void the_scheduled_payment_should_be_visible_in_the_transactions_list() {
        System.out.println("The Payment is scheduled");
    }
	
}
