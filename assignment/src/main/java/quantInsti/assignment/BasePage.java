/**
 * 
 */
package quantInsti.assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Shankar
 *
 */
public class BasePage {

	public WebDriver driver = null;
	public WebDriverWait wait = null;

	public void loadURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		wait = waitInstance(driver, 60);
		wait.until(ExpectedConditions.titleContains("QuantInsti"));
	}

	public WebDriver loadDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Your Browser Path");
			driver = new ChromeDriver();
		} else {
			System.out.println("Opps! Issue while loading the Browser");
		}
		return driver;
	}

	public WebDriverWait waitInstance(WebDriver driver, long timeOutInSeconds) {
		WebDriverWait wait = null;
		wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait;
	}
}
