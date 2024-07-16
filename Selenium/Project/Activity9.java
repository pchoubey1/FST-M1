package Project;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Activity9 {
public static void main(String[] args) {
		
		//Download GeckoDriver
	 WebDriverManager.firefoxdriver().setup();
       //initialize firefox driver
	 WebDriver driver =new FirefoxDriver();
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	
			 //open the browser
	 driver.get("https://v1.training-support.net/selenium/ajax");
	 System.out.println("Page title is :" + driver.getTitle());
	
	// Find and click the "Change content" button on the page.
	 driver.findElement(By.xpath("//button[@class= 'ui violet button']")).click();
	
	 //Wait for the text to say "HELLO!". Print the message that appears on the page.
	 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));// wait condition
	
	 String textmsg = driver.findElement(By.tagName("h1")).getText();// Fetching the Text msg
	 System.out.println("The Text message is : "+ textmsg);//printing text msg
	
	 //Wait for the text to change to contain "I'm late!".
	 wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h3"),"I'm late!")); //Wait condition
	 String delayedtextmsg = driver.findElement(By.tagName("h3")).getText(); // Fetching the Text msg
	 System.out.println("The Delayed Text message is : "+ delayedtextmsg); //printing text msg
	
	 driver.close();
}
}


