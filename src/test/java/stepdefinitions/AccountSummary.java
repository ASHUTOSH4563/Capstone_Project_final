package stepdefinitions;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zerobank.pages.AccountSummaryPage;
import zerobank.pages.HomePage;
import zerobank.pages.LoginPage;
import zerobank.pages.OnlineBankingPage;

public class AccountSummary 
{


	private WebDriver d = Hooks.getDriver(); 
	LoginPage login= new LoginPage(d);
	HomePage home= new HomePage(d);
	OnlineBankingPage bank= new OnlineBankingPage(d);	
	AccountSummaryPage summary= new AccountSummaryPage(d);

	WebDriverWait wait=new WebDriverWait(d, Duration.ofSeconds(10));;
	
     //login page locator
	
	
	
	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
	    d.get("http://zero.webappsecurity.com/");
	    login.clickSigninButton();
	    
	}

	@When("the user enters {string} in the username field and {string} in the password field")
	public void the_user_enters_in_the_username_field_and_in_the_password_field(String username, String password) {

		login.enterUsername(username);
		login.enterPassword(password);
	}

	@When("submits the login form")
	public void submits_the_login_form() {

	    
		login.clickSignin();
		d.navigate().back();
	    d.navigate().back();
	}

	@Then("the user should be taken to the account dashboard")
	public void the_user_should_be_taken_to_the_account_dashboard() {
		String actualTitle="Zero - Personal Banking - Loans - Credit Cards";
	    String expectedTitle=d.getTitle();
	    if(actualTitle.equals(expectedTitle)) {
	    	System.out.println("User is on the Home page");
	    }else {
	    	System.out.println("Login is not successful");
	    }
	}



	@When("User clicks on the Online Banking")
	public void user_clicks_on_the_online_banking() {

		home.clickOnlineBanking();
		
	}

	@When("User clicks on the Account Summary")
	public void user_clicks_on_the_account_summary() {

		bank.clickAccountSummary();
		
	}

	@Then("User should see all account types and balances displayed")
	public void user_should_see_all_account_types_and_balances_displayed() {
		String actualTitle="Zero - Account Summary";
		String expectedTitle=d.getTitle();
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Page loads successfully with account types and balances displayed");
		}else {
			System.out.println("Page is not loaded,Kindly check the previous step !");
		}
	}

	@When("User navigates to the Account Summary page")
	public void user_navigates_to_the_account_summary_page() {


		bank.clickAccountSummary();
		
	}

	@Then("User should see the following account types displayed:")
	public void user_should_see_the_following_account_types_displayed(DataTable accountTypesTable) {
		List<String> accountTypes = accountTypesTable.asList();
		for (String accountType : accountTypes) {
			boolean isDisplayed = summary.isAccountTypeDisplayed(accountType);
			Assert.assertTrue(isDisplayed, accountType + " account type is not displayed!");
		}
	}


}





