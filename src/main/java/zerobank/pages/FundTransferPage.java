package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FundTransferPage extends BasePage {

	public FundTransferPage(WebDriver d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	private By from_Account=By.id("tf_fromAccountId");
	private By to_Account=By.id("tf_toAccountId");
	private By enter_Amount=By.id("tf_amount");
	private By continue_Button=By.id("btn_submit");
	private By submit_Button=By.id("btn_submit");
	private Select options;
	WebDriverWait wait= new WebDriverWait(d, Duration.ofSeconds(10));
	
	public Select makeSelectObj(WebElement e) {
		return new Select(e);
	}
	public void selectFromAccount(String text) {
		options= makeSelectObj(d.findElement(from_Account));
		options.selectByContainsVisibleText(text);
	}
	
	public void selectToAccount(String text) {
		options= makeSelectObj(d.findElement(to_Account));
		options.selectByContainsVisibleText(text);
	}
	
	public void enterAmount(String amount) {
	    d.findElement(enter_Amount).sendKeys(amount);
	}
	
	public void clickContinue() {
	    d.findElement(continue_Button).click();
	}
	public void submit() {
		wait.until(ExpectedConditions.elementToBeClickable(submit_Button)).click();
	}
	public void clearAmount() {
	    d.findElement(By.id("tf_amount")).clear();

	}
}
