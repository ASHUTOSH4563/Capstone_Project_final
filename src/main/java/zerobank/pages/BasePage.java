package zerobank.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
protected WebDriver d;
	public BasePage(WebDriver d) {
		this.d=d;
	}
	public String getTitle() {
		return d.getCurrentUrl();
	}
}
