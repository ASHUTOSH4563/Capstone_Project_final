package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayBillsPage extends BasePage{

	public PayBillsPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		super(d);
	}
	WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

	//New Payee
	private By add_new_payee=By.xpath("//div[@id=\"tabs\"]/ul/li[2]/a");
	private By payee_name=By.id("np_new_payee_name");;
	private By payee_address=By.id("np_new_payee_address");
	private By account=By.id("np_new_payee_account");
	private By payee_details=By.id("np_new_payee_details");
	private By add_btn=By.id("add_new_payee");
	
	//Pay Bills
	private By choose_payee=By.id("sp_payee");
	private By choose_account=By.id("sp_account");
	private By enter_Amount=By.id("sp_amount");
	private By choose_date=By.id("sp_date");
	private By click_pay=By.id("pay_saved_payees");
	
	
	public void clickNewPayee() {
	    wait.until(ExpectedConditions.elementToBeClickable(add_new_payee)).click();
	}
	public void fillValidDetais() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_name)).sendKeys("Siddharth");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_address)).sendKeys("1,abc street");
		wait.until(ExpectedConditions.visibilityOfElementLocated(account)).sendKeys("saving");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_details)).sendKeys("dfsfsdfdsf");
	}
	public void clickAdd() {
		d.findElement(add_btn).click();
	}
	public void fillIncompleteDetails() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_name)).sendKeys("Siddharth");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_address)).sendKeys("1,abc street");
		wait.until(ExpectedConditions.visibilityOfElementLocated(account)).sendKeys("");
		wait.until(ExpectedConditions.visibilityOfElementLocated(payee_details)).sendKeys("dfsfsdfdsf");
	}
	//
	
	public void selectPayee(String payee) {
	    WebElement Payee=d.findElement(choose_payee);
	    Payee.sendKeys(payee);
	}
	public void selectAccount(String account) {
	    WebElement account_Type=d.findElement(choose_account);
	    account_Type.sendKeys(account);
	}
	public void enterAmount(String amount) {
		   d.findElement(enter_Amount).sendKeys(amount);
	}

	public void enterDate(String date) {
		WebElement dateField = d.findElement(choose_date);
		dateField.sendKeys(date);  
	}
	public void clickPay() {
		d.findElement(click_pay).click();
	}
	public void clearAmount() {
		   d.findElement(enter_Amount).clear();
	}
}
