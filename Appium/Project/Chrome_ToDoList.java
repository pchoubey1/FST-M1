package Project;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class Chrome_ToDoList {
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
public void TodoList() throws InterruptedException {
	
	  driver.get("https://v1.training-support.net/selenium");
	  Thread.sleep(10000);
	
	  String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
	 //Scroll using UiScrollable
	  driver.findElement(AppiumBy.androidUIAutomator(UiScrollable +".scrollForward(5)"));
	  driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"To-Do List  Elements get added at run time \"]")).click();
	 
	  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@text=\"To-Do List\"]")));
	  Thread.sleep(10000);
	  // enter task and add task 1
	   driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Add tasks to list");
	   driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
	  // enter task and add task 2
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Get number of tasks");
	  driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
	  // enter task and add task 3
	  driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Clear the list");
	  driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
	  //get the count of task
	  //System.out.println("The number of task in the list :"+ driver.findElements(AppiumBy.xpath("//android.view.View[@resource-id=\"tasksList\"]")).size());
	 
	  //Click on each of the tasks added to strike them out.
	  driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"tasksList\"][0]")).click();
	  driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"tasksList\"][1]")).click();
	  driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"tasksList\"][2]")).click();
	  Thread.sleep(1000);
	  //clear list
	  driver.findElement(AppiumBy.xpath("//android.view.View[@text=\" Clear List\"]")).click();
	  driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
	  //Add assertions 
	  String message=driver.findElement(AppiumBy.id("message")).getText();
	  Assert.assertEquals(message, "Please enter a task first.");
}
// Tear down method
@AfterClass
public void tearDown() {
    // Close the app
    driver.quit();
}

}


