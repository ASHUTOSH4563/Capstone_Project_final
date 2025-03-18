//package zerobank.pages;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Select;
//
//public class OnlineStatementsPage {
//     WebDriver d;
//
//     By signinButton = By.id("signin_button");
//     By usernameInput = By.id("user_login");
//     By passwordInput = By.id("user_password");
//     By signinBtn = By.xpath("//input[@value='Sign in']");
//    // By onlineBankingLink = By.xpath("//*[@id='onlineBankingMenu']");
//     By onlineBankingLink=By.id("onlineBankingMenu");
//     By onlineStatementsLink = By.id("online_statements_link");
//     By accountDropdown = By.id("os_accountId");
//    // By year = By.xpath("//a[contains(text(),'2012')]");
//     By year =By.xpath("//*[@id=\"online_statements_for_account\"]/div/div/div[2]/ul/li[1]/a");
//     By statementLink = By.xpath("//a[contains(text(),'Statement 01/10/12(57K)')]"); 
//
//    public OnlineStatementsPage(WebDriver d) {
//        this.d = d;
//    }
//    
//    public void clickSigninButton() {
//        d.findElement(signinButton).click();
//    }
//
//    public void enterUsername(String username) {
//        d.findElement(usernameInput).sendKeys(username);
//    }
//
//    public void enterPassword(String password) {
//        d.findElement(passwordInput).sendKeys(password);
//    }
//
//    public void clickSignIn() {
//        d.findElement(signinBtn).click();
//    }
//
//	public void clickOnlineBanking() {
//        d.findElement(onlineBankingLink).click();
//    }
//
//    public void clickOnlineStatements() {
//        d.findElement(onlineStatementsLink).click();
//    }
//
//    public void selectAccount() {
//        WebElement accountElement = d.findElement(accountDropdown);
//        Select accounts = new Select(accountElement);
//		accounts.selectByVisibleText("Loan"); 
//    }
//
//    public void selectYear() {
//        d.findElement(year).click(); 
//    }
//
//    public boolean areStatementsDisplayed() {
//        return true; 
//    }
//
//    public void clickOnStatementForDownload() {
//        d.findElement(statementLink).click();
//    }
//}

package zerobank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnlineStatementsPage {
    private WebDriver d;
    private WebDriverWait wait;

    private By signinButton = By.id("signin_button");
    private By usernameInput = By.id("user_login");
    private By passwordInput = By.id("user_password");
    private By signinBtn = By.xpath("//input[@value='Sign in']");
    private By onlineBankingLink=By.xpath("//*[@id=\"onlineBankingMenu\"]/div/strong");
    private By onlineStatementsLink = By.id("online_statements_link");
    private By accountDropdown = By.id("os_accountId");
    private By year = By.xpath("//*[@id=\"online_statements_for_account\"]/div/div/div[2]/ul/li[1]/a");
    private By statementLink = By.xpath("//a[contains(text(),'Statement 01/10/12(57K)')]");

    public OnlineStatementsPage(WebDriver d) {
        this.d = d;
        this.wait = new WebDriverWait(d, Duration.ofSeconds(10)); // Initialize WebDriverWait
    }

    public void clickSigninButton() {
        WebElement signinBtnElement = wait.until(ExpectedConditions.elementToBeClickable(signinButton));
        signinBtnElement.click();
    }

    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement signin = wait.until(ExpectedConditions.elementToBeClickable(signinBtn));
        signin.click();
    }

    public void clickOnlineBanking() {
        WebElement onlineBanking = wait.until(ExpectedConditions.elementToBeClickable(onlineBankingLink));
        onlineBanking.click();
    }

    public void clickOnlineStatements() {
        WebElement onlineStatements = wait.until(ExpectedConditions.elementToBeClickable(onlineStatementsLink));
        onlineStatements.click();
    }

    public void selectAccount() {
        WebElement accountElement = wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));
        Select accounts = new Select(accountElement);
        accounts.selectByVisibleText("Loan");
    }

    public void selectYear() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(year));
                yearElement.click();
                System.out.println("User selects the desired year.");
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale Element Reference Exception encountered while selecting year. Retrying...");
            }
            attempts++;
        }
        throw new RuntimeException("Failed to select year after multiple attempts.");
    }


    public boolean areStatementsDisplayed() {
        WebElement statementElement = wait.until(ExpectedConditions.presenceOfElementLocated(statementLink));
        return statementElement.isDisplayed();
    }

    public void clickOnStatementForDownload() {
        int attempts = 0;
        while (attempts < 3) { 
            try {
                WebElement statementDownload = wait.until(ExpectedConditions.elementToBeClickable(statementLink));
                statementDownload.click();
                System.out.println("User clicked on download Account Statements.");
                return; 
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale Element Reference Exception encountered. Retrying...");
            }
            attempts++;
        }
        throw new RuntimeException("Failed to click on statement download link after multiple attempts.");
    }

}
