package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineBankingPage extends BasePage{

	public OnlineBankingPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		super(d);
	}

	private By account_summary= By.xpath("//span[@id=\"account_summary_link\"]");
	private By account_activity=By.xpath("//span[@id=\"account_activity_link\"]");
	private By pay_bills=By.xpath("//span[@id=\"pay_bills_link\"]");
	private By money_map=By.xpath("//span[@id=\"money_map_link\"]");
	private By online_statement=By.xpath("//span[@id=\"online_statements_link\"]");
	
	WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(10));
	public void clickAccountSummary() {
		wait.until(ExpectedConditions.elementToBeClickable(account_summary)).click();
	}
	public void clickAccountActivity() {
		wait.until(ExpectedConditions.elementToBeClickable(account_activity)).click();
	}
	public void clickPayBill() {
		wait.until(ExpectedConditions.elementToBeClickable(pay_bills)).click();
	}
	public void clickMoneyMap() {
		wait.until(ExpectedConditions.elementToBeClickable(money_map)).click();
	}
	public void clickOnlineStatement() {
		wait.until(ExpectedConditions.elementToBeClickable(online_statement)).click();
	}
}

