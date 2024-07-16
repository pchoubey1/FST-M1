package project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Activity1 {

	AndroidDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Desired Capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.apps.tasks");
		options.setAppActivity(".ui.TaskListsActivity");
		options.noReset();

		// Server Address
		URL serverURL = new URL("http://localhost:4723/");

		// Driver Initialization
		driver = new AndroidDriver(serverURL, options);
	}

	// Test method
	@Test
	public void taskCreation() {

		driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
		driver.findElement(AppiumBy.id("add_task_title")).sendKeys("Complete Activity with Google Tasks");
		driver.findElement(AppiumBy.id("add_task_done")).click();

		driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
		driver.findElement(AppiumBy.id("add_task_title")).sendKeys("Complete Activity with Google Keep");
		driver.findElement(AppiumBy.id("add_task_done")).click();

		driver.findElement(AppiumBy.accessibilityId("Create new task")).click();
		driver.findElement(AppiumBy.id("add_task_title")).sendKeys("Complete the second Activity Google Keep");
		driver.findElement(AppiumBy.id("add_task_done")).click();

		driver.findElement(AppiumBy.accessibilityId("My Tasks")).click();

		List<WebElement> taskItems = driver.findElements(AppiumBy.id("task_name"));
		System.out.println("Tasklist count is: " + taskItems.size());
		
		for(WebElement taskItem : taskItems)
		{
			System.out.println("Task is : ");
			System.out.println(taskItem.getText());
		}
	}

	// Tear down method
	@AfterClass
	public void tearDown() {
		// Close the app
		driver.quit();
	}

}
