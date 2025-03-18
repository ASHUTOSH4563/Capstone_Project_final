package zerobank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class AccountSummaryPage extends BasePage{

	public AccountSummaryPage(WebDriver d) {

		super(d);// TODO Auto-generated constructor stub
	}

	private By cashAccount = By.xpath("//h2[text()='Cash Accounts']");
	private By investmentAccount = By.xpath("//h2[text()='Investment Accounts']");
	private By creditAccount = By.xpath("//h2[text()='Credit Accounts']");
	private By loanAccount = By.xpath("//h2[text()='Loan Accounts']");
	
	public boolean isAccountTypeDisplayed(String accountType) {
		By locator;
		switch (accountType) {
			case "Cash Accounts":
				locator = cashAccount;
				break;
			case "Investment Accounts":
				locator = investmentAccount;
				break;
			case "Credit Accounts":
				locator = creditAccount;
				break;
			case "Loan Accounts":
				locator = loanAccount;
				break;
			default:
				return false;
		}
		try {
			return d.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
