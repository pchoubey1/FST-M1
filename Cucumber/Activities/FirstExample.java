package StepDefinition;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstExample extends Base {
	@BeforeAll
	public static void Setup() {
		// Set up Firefox driver
		WebDriverManager.firefoxdriver().setup();
		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();
	}

	@AfterAll
	public static void teardown() {
		driver.quit();
	}

	@Given("The user is on TS homepage")
	public void TSHomepage() {
		driver.get("https://v1.training-support.net");
		Assertions.assertEquals("Training Support", driver.getTitle());
	}

	@When("User click on the about Us link")
	public void clickAboutLink() {
		driver.findElement(By.id("about-link")).click();
	}

	@Then("the user redirected to about page")
	public void verifyAboutPage() {
		Assertions.assertEquals("About Training Support", driver.getTitle());
	}
}
