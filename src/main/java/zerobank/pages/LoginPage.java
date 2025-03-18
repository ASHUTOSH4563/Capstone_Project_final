package zerobank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver d){
		super(d);
	}
	
	WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));


    
    private By signinButton = By.id("signin_button");
	private By usernameInput = By.id("user_login");
	private By passwordInput = By.id("user_password");
	private By signinBtn= By.name("submit");
   


    public void clickSigninButton() {
        d.findElement(signinButton).click();
       // wait.until(ExpectedConditions.elementToBeClickable(signinButton));  
    }

    public void enterUsername(String username) {
        d.findElement(usernameInput).sendKeys(username);
    	
    }

    public void enterPassword(String password) {
        d.findElement(passwordInput).sendKeys(password);
        
    }

    public void clickSignin() {
        d.findElement(signinBtn).click();
    	// wait.until(ExpectedConditions.elementToBeClickable(signinBtn));  
    }

    public void getErrorMessage() {
        System.out.println("Incorrect username or password");
    }
}
