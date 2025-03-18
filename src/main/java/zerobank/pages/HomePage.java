package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	WebDriverWait wait=new WebDriverWait(d,Duration.ofSeconds(10));
	private By online_banking=By.id("onlineBankingMenu");
	private By transfer_fund=By.xpath("//span[@id=\"transfer_funds_link\"]");
	private By checking_account_Activity=By.xpath("//span[@id=\"account_activity_link\"]");
	private By money_map=By.xpath("//span[@id=\"money_map_link\"]");
	
	public void clickOnlineBanking() {
		wait.until(ExpectedConditions.elementToBeClickable(online_banking)).click();
	}
	public void clickTransferFund() {
		wait.until(ExpectedConditions.elementToBeClickable(transfer_fund)).click();
	}
	public void clickMoneyMap() {
		wait.until(ExpectedConditions.elementToBeClickable(money_map)).click();

	}
	public void clickCheckingAccountActivity() {
		wait.until(ExpectedConditions.elementToBeClickable(checking_account_Activity)).click();

	}
}