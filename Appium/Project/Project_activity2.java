import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Project_activity2 {
	// Driver Declaration
    AndroidDriver driver;

 // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
    	 UiAutomator2Options options = new UiAutomator2Options();
         options.setPlatformName("android");
         options.setAutomationName("UiAutomator2");
         options.setAppPackage("com.google.android.keep");
         options.setAppActivity(".activities.BrowseActivity");
         options.noReset();

         // Server Address
            URL serverURL = new URL("http://localhost:4723/");
     
            // Driver Initialization
            driver = new AndroidDriver(serverURL, options);
        }
     
    // Test method
    @Test
    public void addNotes() {
        
       // driver.findElement(AppiumBy.id("Keep Notes")).click();
        driver.findElement(AppiumBy.accessibilityId("New text note")).click();
        driver.findElement(AppiumBy.id("com.google.android.keep:id/editable_title")).sendKeys("Hello");
        driver.findElement(AppiumBy.id("com.google.android.keep:id/edit_note_text")).sendKeys("Congratulations");
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
         String result = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.keep:id/index_note_title\" and @text=\"Hello\"]")).getText();
        // String result1 = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.keep:id/index_note_text_description\" and @text=\"Congratulations\r\n")).getText();
         
    
    }
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app)
        driver.quit();
    }
}