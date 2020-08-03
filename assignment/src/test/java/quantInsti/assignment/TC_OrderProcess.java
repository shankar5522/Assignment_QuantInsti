package quantInsti.assignment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import quantInsti.assignment.pages.OrderProcess_Page;

public class TC_OrderProcess {

	public WebDriver driver = null;
	public OrderProcess_Page order = null;

	@BeforeTest
	@Parameters({ "browser", "url" })
	public void setUp(String browser, String url) throws InterruptedException {
		BasePage basePage = new BasePage();
		driver = basePage.loadDriver(browser);
		basePage.loadURL(url);
		order = new OrderProcess_Page(driver);
		order.login("UserName", "Password");
	}

	@Test
	public void userRegistration() throws InterruptedException {
		System.out.println("IN TEST MENTOHD");
		order.orderBook();

	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		order.signOff();
		driver.quit();
		order = null;
		driver = null;
	}
}
