package Project;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Activity3 {
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
		options.setAppPackage("com.google.android.keep");
		options.setAppActivity(".activities.BrowseActivity");
		options.noReset();

		// Server Address
		URL serverURL = new URL("http://localhost:4723/");

		// Driver Initialization
		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void keepNotes() {

		driver.findElement(AppiumBy.accessibilityId("New text note")).click();
		driver.findElement(AppiumBy.id("editable_title")).sendKeys("Google Task activity3");
		driver.findElement(AppiumBy.id("edit_note_text")).sendKeys("Create New Note we are adding notes here");
		driver.findElement(AppiumBy.accessibilityId("Reminder")).click();

		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.google.android.keep:id/menu_text\" and @text=\"Pick a date & time\"]")));
		driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.google.android.keep:id/menu_text\" and @text=\"Pick a date & time\"]"))
				.click();
		driver.findElement(AppiumBy.accessibilityId("Date - Currently selected - July 14")).click();
		driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.google.android.keep:id/text\" and @text=\"Today\"]"))
				.click();
		// driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.google.android.keep:id/text\"
		// and @text=\"Afternoon\"]")).click();
		driver.findElement(AppiumBy.id("time_spinner")).click();
		driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"com.google.android.keep:id/text\" and @text=\"Night\"]"))
				.click();
		driver.findElement(AppiumBy.id("save")).click();
		// Assertion
		String messageTextSent = driver.findElement(AppiumBy.accessibilityId("Time reminder Today, 8:00 PM")).getText();
		driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();
		System.out.println(messageTextSent);
		Assert.assertEquals(messageTextSent, "Today, 8:00 PM");
	}
}
