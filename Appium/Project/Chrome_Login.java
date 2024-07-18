package Project;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Chrome_Login {
	AndroidDriver driver;
	WebDriverWait wait;
@BeforeClass
public void setup() throws MalformedURLException {
	UiAutomator2Options options = new UiAutomator2Options();
	options.setPlatformName("android");
	options.setAutomationName("UiAutomator2");
	options.setAppPackage("com.android.chrome");
	options.setAppActivity("com.google.android.apps.chrome.Main");
	options.noReset();
	
	URL serverUrl = new URL("http://localhost:4723/");
	
	driver = new AndroidDriver(serverUrl, options);
	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	
}
@Test
public void LoginFormTest() throws Exception
{
	 driver.get("https://v1.training-support.net/selenium");
	  Thread.sleep(10000);
	
	  String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
	  //Scroll using UiScrollable
	 driver.findElement(AppiumBy.androidUIAutomator(UiScrollable +".scrollForward(14) .scrollTextIntoView(\"Login Form  Please sign in. \")"));
	
	
	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@text=\"Login Form  Please sign in. \"]")));
	 
	  Thread.sleep(10000);
	  driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"Login Form  Please sign in. \"]")).click();
}
@AfterTest
 public void Test1() {
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")).sendKeys("admin");
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")).sendKeys("password");
	  driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Log in\"]")).click();
	  String message = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"action-confirmation\"]")).getText();
	  Assert.assertEquals(message, "Welcome Back, admin");
	 
 }
@AfterTest
 public void Test2() {
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")).sendKeys("user");
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")).sendKeys("pass");
	  driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Log in\"]")).click();
	  String message = driver.findElement(AppiumBy.id("action-confirmation")).getText();
	  Assert.assertEquals(message, "Invalid Credentials");
	 
}
// Tear down method
public void tearDown() {
    // Close the app
    driver.quit();
}




 
}


