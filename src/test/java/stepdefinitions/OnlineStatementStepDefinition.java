package stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import zerobank.pages.OnlineStatementsPage;

public class OnlineStatementStepDefinition {
	
   private WebDriver d =Hooks.getDriver();
    OnlineStatementsPage onlinestatementspage;
    

	@When("User enters valid username {string} and password {string}")
	public void user_enters_valid_username_and_password(String username, String password) {
		onlinestatementspage = new OnlineStatementsPage(d);
		onlinestatementspage.enterUsername(username);
        onlinestatementspage.enterPassword(password);
        System.out.println("User enters valid Username and password");
	}

	@When("User clicks the Online Banking")
	public void user_clicks_the_online_banking() {
		onlinestatementspage.clickOnlineBanking();
        System.out.println("User clicks on Online banking option");
	}

	@Then("User clicks on the Online Statements")
	public void user_clicks_on_the_online_statements() {
		onlinestatementspage.clickOnlineStatements();
        System.out.println("User clicks on Online statements menu");
	}

	@Then("Online Statements page should be displayed")
	public void online_statements_page_should_be_displayed() {
        Assert.assertTrue(d.getTitle().equals("Zero - Online Statements"));
        System.out.println("User is on Online Statements page");
	}

	@When("User selects account type")
	public void user_selects_account_type() {
		onlinestatementspage.selectAccount();
        System.out.println("User selects the type of account");
	}

	@When("User selects year")
	public void user_selects_year() {
		onlinestatementspage.selectYear();
        System.out.println("User selects the desired year");
	}

	@Then("Statements should be displayed for selected account and year")
	public void statements_should_be_displayed_for_selected_account_and_year() {
		onlinestatementspage.areStatementsDisplayed();
    	WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
		Assert.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Statement 01/10/12(57K)')]"))) != null,"Element is NOT present!");
		System.out.println("Account Statements displayed");
	}

	@When("User clicks on a statement for download")
	public void user_clicks_on_a_statement_for_download() {
		onlinestatementspage.clickOnStatementForDownload();
		System.out.println("user clicks on download Account Statements");
	}

	@Then("The file statement.pdf should be downloaded successfully")
	public void the_file_statement_pdf_should_be_downloaded_successfully() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)d;		
		File f = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("./screenshots/downloadoutput.jpg"));
		System.out.println("File Downloaded");
	}


}

