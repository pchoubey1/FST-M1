package Activity;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class Activity5 {
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
        options.setAppPackage("com.google.android.apps.messaging");
        options.setAppActivity(".ui.ConversationListActivity");
        options.noReset();
 
        // Server Address
        URL serverURL = new URL("http://localhost:4723/");
 
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
    // Test method
    @Test
    public void smsTest() {
        // Find and click the add button
        driver.findElement(AppiumBy.accessibilityId("Start chat")).click();
 
        // Wait for elements to load
        /*wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("recipient_text_view")
        ));*/
 
        // Find the element to add recipient
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"ContactSearchField\"]")).sendKeys("8250800313");
        
        // Press ENTER
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
 
        // Wait for textbox to appear
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("compose_message_text")));
 
        // Enter text to send
        driver.findElement(AppiumBy.id("compose_message_text")).sendKeys("Hello from Appium");
        // Press Send
        driver.findElement(AppiumBy.accessibilityId("Send SMS")).click();
 
        // Assertion
        String messageTextSent = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"message_text\"]")).getText();
        Assert.assertEquals(messageTextSent, "Hello from Appium");
    }
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
