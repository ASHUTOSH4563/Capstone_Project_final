//package hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import org.openqa.selenium.edge.EdgeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class Hooks {
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//   
//
//    @Before
//    public void setup() {
//        if (driver.get() == null) {
//            String browser = System.getProperty("browser", "chrome");
//
//            switch (browser.toLowerCase()) {
//                case "chrome":
//                    WebDriverManager.chromedriver().setup();
//                    driver.set(new ChromeDriver());
//                    break;
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    driver.set(new FirefoxDriver());
//                    break;
//                case "edge":
//                    WebDriverManager.edgedriver().setup();
//                    driver.set(new EdgeDriver());
//                    break;
//                default:
//                    throw new IllegalArgumentException("Invalid browser specified: " + browser);
//            }
//
//            // Ensure driver is fully initialized before using it
//            WebDriver initializedDriver = driver.get();
//            if (initializedDriver == null) {
//                throw new IllegalStateException("WebDriver failed to initialize.");
//            }
//
//            initializedDriver.manage().window().maximize();
//        }
//    }
//
//
//    public static WebDriver getDriver() {
//        if (driver.get() == null) {
//            throw new IllegalStateException("WebDriver is not initialized! Ensure Hooks' @Before method runs first.");
//        }
//        return driver.get();
//    }
//
//
//    @After
//    public void tearDown() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}
package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;

public class Hooks {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ExtentReports extent;
    private static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        if (extent == null) {
            // Create a report file inside "test-output" directory
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        test = extent.createTest(scenario.getName()); // Attach test case to the report

        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome");

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser specified: " + browser);
            }
            driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized! Ensure Hooks' @Before method runs first.");
        }
        return driver.get();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario Failed: " + scenario.getName());
        } else {
            test.pass("Scenario Passed: " + scenario.getName());
        }

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

        extent.flush(); // Ensure report is saved
    }
}


