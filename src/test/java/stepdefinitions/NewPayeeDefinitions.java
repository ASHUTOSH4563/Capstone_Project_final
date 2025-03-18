package stepdefinitions;
import hooks.Hooks;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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

import zerobank.pages.HomePage;
import zerobank.pages.LoginPage;
import zerobank.pages.OnlineBankingPage;
import zerobank.pages.PayBillsPage;
public class NewPayeeDefinitions {
	private WebDriver cd=Hooks.getDriver();
	
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
	private By signinButton = By.id("signin_button");
	private By usernameInput = By.id("user_login");
	private By passwordInput = By.id("user_password");
	private By signinBtn= By.name("submit");
	
	
	private By pay_bills=By.xpath("//span[@id=\"pay_bills_link\"]");
	private By online_banking=By.id("onlineBankingMenu");
	
	
	private By add_new_payee=By.xpath("//div[@id=\"tabs\"]/ul/li[2]/a");
	private By payee_name=By.id("np_new_payee_name");;
	private By payee_address=By.id("np_new_payee_address");
	private By account=By.id("np_new_payee_account");
	private By payee_details=By.id("np_new_payee_details");
	private By add_btn=By.id("add_new_payee");
	
	
	LoginPage login= new LoginPage(cd);
	HomePage home= new HomePage(cd);
	OnlineBankingPage bank= new OnlineBankingPage(cd);
	PayBillsPage newPayee= new PayBillsPage(cd);
	String homeUrl="http://zero.webappsecurity.com/index.html";
	WebDriverWait wait = new WebDriverWait(cd, Duration.ofSeconds(10));
	
	@Given("you are in the hompage")
	public void you_are_in_the_hompage() {	
		
		cd.get(homeUrl);
		//cd.manage().window().maximize();
	}

	@When("click login")
	public void click_login() {
		cd.findElement(signinButton).click();
		//login.clickSigninButton();
	}

	@When("Enter the {string} and {string} and login")
	public void enter_the_and_and_login(String string, String string2) {
		cd.findElement(usernameInput).sendKeys(string);
		cd.findElement(passwordInput).sendKeys(string2);

	}

	@Then("I get logged in and redirect to the home page")
	public void i_get_logged_in_and_redirect_to_the_home_page() {
		cd.findElement(signinBtn).click();
		cd.navigate().back();

	}

	@Given("I am in the Homepage")
	public void i_am_in_the_homepage() {
		cd.navigate().back();
		System.out.println("I am in the home page");
	}

	@When("I click on online banking")
	public void i_click_on_online_banking() {
		cd.findElement(online_banking).click();
		
	}

	@When("I click on pay bills")
	public void i_click_on_pay_bills() {
		cd.findElement(pay_bills).click();
		
	}

	@When("I click on new payee")
	public void i_click_on_new_payee() {
	    wait.until(ExpectedConditions.elementToBeClickable(add_new_payee)).click();
		
	}

	@When("fill out the new payee details")
	public void fill_out_the_new_payee_details() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_name)).sendKeys("Siddharth");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_address)).sendKeys("1,abc street");
		wait.until(ExpectedConditions.visibilityOfElementLocated(account)).sendKeys("saving");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_details)).sendKeys("dfsfsdfdsf");
		
		}

	@When("click Add")
	public void click_add() {
		cd.findElement(add_btn).click();
		
	}

	@Then("I make a new payee")
	public void i_make_a_new_payee() {
		System.out.println("Made a new payee");
		
	}

	@Given("Navigate to homepage")
	public void navigate_to_homepage() {
		cd.navigate().back();

	}

	@When("clicked on the online banking")
	public void clicked_on_the_online_banking() {
		cd.findElement(online_banking).click();
		

	}

	@When("click on pay bills")
	public void click_on_pay_bills() {
		cd.findElement(pay_bills).click();
		

	}

	@When("click on new payee")
	public void click_on_new_payee() {
		cd.findElement(add_new_payee).click();
		

	}

	@When("fill incomplete details")
	public void fill_incomplete_details() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_name)).sendKeys("Siddharth");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_address)).sendKeys("1,abc street");
		wait.until(ExpectedConditions.visibilityOfElementLocated(account)).sendKeys("");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_details)).sendKeys("dfsfsdfdsf");
		
	}

	@When("click add button")
	public void click_add_button() {
		cd.findElement(add_btn).click();
		

	}

	@Then("confirm new payee not added")
	public void confirm_new_payee_not_added() {
		System.out.println("not made a new payee");

	}

	

}
