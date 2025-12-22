package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class TestNGSeleniumJava101_Chrome {



    private WebDriverWait wait;
    private WebDriver driver;


    @BeforeMethod
    public void setup() throws IOException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("128.0");
        browserOptions.addArguments("--headless=new");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "poojagaydhani");
        ltOptions.put("accessKey", "LT_tbkX5LXDwgRxTaSpkc0Q57IWwV22UDmvRs0nei2mFYHEvqE");
        ltOptions.put("build", "TestNG Assignment");
        ltOptions.put("project", "SeleniumAutomation1912");
        ltOptions.put("name", "Assignment1");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);


            driver = new RemoteWebDriver(new URL("https://poojagaydhani:LT_tbkX5LXDwgRxTaSpkc0Q57IWwV22UDmvRs0nei2mFYHEvqE@hub.lambdatest.com/wd/hub"), browserOptions);
            driver.get("https://www.lambdatest.com/selenium-playground/");
            wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Explicit wait setup


        }

    @Test(priority = 0)
    public void testScenario1() throws InterruptedException {


        // Perform explicit wait till the title is available
        wait.until(ExpectedConditions.titleIs("Selenium Grid Online | Run Selenium Test On Cloud"));

        // SoftAssert for validating the Page Title
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), "Selenium Grid Online | Run Selenium Test On Cloud", "Page title is incorrect!");

        // Following statements will still execute even if the assertion fails
        System.out.println("This statement executes after soft assert.");

        // Collate all soft assertions
        softAssert.assertAll();
    }

    // Test Scenario 2: Checkbox Validation

    @Test(priority = 1)

    public void testScenario2() throws InterruptedException {

        driver.findElement(By.linkText("Checkbox Demo")).click();

        // Click the checkbox under "Single Checkbox Demo"
        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));
        singleCheckbox.click();

        // Validate that the checkbox is selected
        SoftAssert softAssert = new SoftAssert();
//    WebElement singleCheckboxselected = driver.findElement(By.id("txtAge"));
        softAssert.assertTrue(singleCheckbox.isSelected(), "Checkbox is selected!");

        // Click the checkbox again to unselect
        singleCheckbox.click();

        // Validate that the checkbox is unselected
        softAssert.assertFalse(singleCheckbox.isSelected(), "Checkbox is not selected!");

        // Assert all soft assertions
        softAssert.assertAll();
    }

// Test Scenario 3: JavaScript Alert Validation

@Test(priority = 2)
    public void testScenario3() throws InterruptedException {
        // Navigate to "Javascript Alerts"
        driver.findElement(By.linkText("Javascript Alerts")).click();
Thread.sleep(3000);

        // Click the "Click Me" button in the "Java Script Alert Box" section
        driver.findElement(By.xpath("//button[text()='Click Me']")).click();

        // Switch to the alert and validate the message
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertMessage, "I am an alert box!", "Alert message does not match!");
        alert.accept();
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() throws Exception
    { if (driver != null) {
        try (FileWriter writer = new FileWriter("dom_snapshot.html"))
        { writer.write(driver.getPageSource()); } driver.quit();
    } }

}
