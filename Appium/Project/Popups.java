package Project;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Popups {
	// Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;
 
    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        
        options.noReset();
 
        // Server Address
        URL serverURL = new URL("http://localhost:4723/");
 
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        // Open the page in Chrome
        driver.get("https://v1.training-support.net/selenium");
    }
 
    // Test method
    @Test
    public void chromeTest() {
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
 
        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className("android.view.View")));
        List<WebElement> Elements = driver.findElements(AppiumBy.className("android.view.View"));
        
        // Print the number of images
        System.out.println("Before scroll: " + Elements.size());
 
        // Scroll using UiScrollable
        driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".scrollTextIntoView(\"Popups \")")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Sign In\"]")));
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Sign In\"]")).click();
    }
       @AfterTest
        public void Test1()
        { 
        	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("username")));
        	driver.findElement(AppiumBy.id("username")).sendKeys("admin");
        	driver.findElement(AppiumBy.id("password")).sendKeys("password");
        	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Log in\"]")).click();
        	String message =driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"action-confirmation\"]")).getText();
        	Assert.assertEquals(message, "Welcome Back, admin");
        }
       @AfterTest
       public void Test2()
        {
        	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Sign In\"]")).click();
        	driver.findElement(AppiumBy.id("username")).sendKeys("admi");
        	driver.findElement(AppiumBy.id("password")).sendKeys("passwor");
        	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Log in\"]")).click();
        	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Log in\"]")).click();
        	String message =driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"action-confirmation\"]")).getText();
        	Assert.assertEquals(message, "Invalid Credentials");
        }
       
    
 
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
