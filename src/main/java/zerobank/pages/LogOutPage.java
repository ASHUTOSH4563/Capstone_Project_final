package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage extends BasePage{

	public LogOutPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		super(d);
	}
	WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

	private By profile_dropdown=By.xpath("//*[@id=\"settingsBox\"]/ul/li[3]/a");
	private By logout=By.xpath("//*[@id=\"logout_link\"]");
	
	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(profile_dropdown)).click();
	    wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
	}

	public boolean checkHomepage() {
		return d.getCurrentUrl().contains("index.html");
	}



}
