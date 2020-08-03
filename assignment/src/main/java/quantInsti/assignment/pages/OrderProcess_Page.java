package quantInsti.assignment.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import quantInsti.assignment.BasePage;

public class OrderProcess_Page {

	WebDriver driver = null;
	BasePage basePage = null;
	WebDriverWait wait = null;
	Actions actions = null;

	/**
	 * @param driver
	 */
	public OrderProcess_Page(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.basePage = new BasePage();
		this.wait = basePage.waitInstance(driver, 50);
		actions = new Actions(driver);
	}

	String fullCourseName = null, name = null, price = null;
	By logIn = By.xpath("//*[text()='Login']");
	By userName = By.xpath("//*[@name='email']");
	By password = By.xpath("//*[@name='password']");
	By profileIcon = By.xpath(
			"//nav[@id='right-navigation']/child::ul/child::div/child::li/child::div[contains(@class,'profile-pic-initials')]");
	By moduleAlert = By.xpath("//*[text()='Remind me later']");
	By signOut = By.xpath("(//*[text()='Logout'])[2]");
	By browseCourse = By.partialLinkText("Browse Courses");
	By courseName = By.xpath("(//*[text()='Sentiment Analysis in Trading'])[1]");
	By courseDetails = By.xpath("//h1[contains(text(),'Learning Track')]");
	By coursePrice = By.xpath("//span[contains(@class,'striked')]/following-sibling::span/span");
	By enrollButton = By.xpath("(//span[contains(text(),'Enroll Now')])[2]");
	//By addedCourses = By.xpath("//div[@class='cart-header']/following-sibling::div");
	//By addedCourses = By.xpath("//h5[@class='cart-item-title']");
	By cartcount = By.xpath("(//span[@class='cart-count'])[1]");
	By baseAmt = By.xpath("//div[text()='Base Amount']/following-sibling::div");
	By amtPayable = By.xpath("//div[@class='cart-summary-right']/h5/span");
	By checkout = By.xpath("//span[text()='Checkout']");
	By viewDetails = By.xpath("(//*[@class='cart-item']/descendant::a[text()='View Details'])[1]");
	By remove = By.xpath("(//*[@class='cart-item']/descendant::a[text()='Remove'])[1]");
	By applyCouponBtn = By.xpath("//span[text()='Apply Coupon']");
	By modelClose = By.xpath("//button[@type='button' and @class='close']");
	By modelInputCoupon = By.xpath("//input[@type='text']");
	By modelApplyBtn = By.xpath("//span[text()='Apply']");
	By modelCouponErrorMsg = By.xpath("//span[contains(text(),'no longer valid')]");
	By chatMsg = By.xpath("//*[contains(text(),'welcome to Quantra, do you need help?')]");
	By iframe = By.xpath("//iframe[@title='chatbot']");
	By chatClose = By.xpath("(//*[@class='iticks-top-bar-button'])[1]");

	public void signOff() {
		driver.findElement(profileIcon).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(signOut));
		driver.findElement(signOut).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(logIn));
		System.out.println("SignOUT SUCCESSFUL");
	}

	public void login(String userName, String password) {

		//Inital Page Load
		wait.until(ExpectedConditions.elementToBeClickable(logIn));
		driver.findElement(logIn).click();

		//actual Login Page
		wait.until(ExpectedConditions.elementToBeClickable(logIn));
		driver.findElement(this.userName).sendKeys(userName);
		driver.findElement(this.password).sendKeys(password);
		driver.findElement(logIn).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(moduleAlert));
		driver.findElement(moduleAlert).click(); //Remind me later or Sure web alert box

		//wait to display some text
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
		System.out.println("LOGIN SUCCESSFUL");

	}

	public void orderBook() {
		System.out.println("IN Order Book Page");
		//Select Course
		wait.until(ExpectedConditions.visibilityOfElementLocated(browseCourse));
		actions.moveToElement(driver.findElement(browseCourse)).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(courseName));
		driver.findElement(courseName).click();

		//Course name and Price
		wait.until(ExpectedConditions.visibilityOfElementLocated(courseDetails));
		fullCourseName = driver.findElement(courseDetails).getText();
		System.out.println("FULL COURSE NAME : " + fullCourseName);
		name = getTheCourseName(fullCourseName);
		System.out.println("Name of the COuse is : " + name);
		price = driver.findElement(coursePrice).getText();
		System.out.println("Price of the COuse is : " + price);

		disableChatIcon();

		wait.until(ExpectedConditions.visibilityOfElementLocated(enrollButton));
		driver.findElement(enrollButton).click();

		verifyCourseDetails();
		viewDetails();
		removeItem();
		applyCoupon();
	}

	public void applyCoupon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
		driver.findElement(applyCouponBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(modelClose));
		driver.findElement(modelInputCoupon).sendKeys("ABC");
		driver.findElement(modelApplyBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(modelCouponErrorMsg));
		driver.findElement(modelClose).click();
	}

	public void removeItem() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
		driver.findElement(remove).click();
	}

	public void viewDetails() {
		String mainTab = driver.getWindowHandle();
		driver.findElement(viewDetails).click();
		Set<String> listOfTabs = driver.getWindowHandles();
		for (String tab : listOfTabs) {
			if (!tab.equals(mainTab)) {
				driver.switchTo().window(tab);
				wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
				driver.close();
				break;
			}
		}
		driver.switchTo().window(mainTab);
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
	}

	public void verifyCourseDetails() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(baseAmt));
		String baseAmount = driver.findElement(baseAmt).getText();
		System.out.println("Your Base Amount is : " + baseAmount);

		String amountPayable = driver.findElement(amtPayable).getText();
		System.out.println("Your Actual Amount pay after Tax is " + amountPayable);
	}

	public String getTheCourseName(String fullCourseName) {
		int lenght = fullCourseName.length();
		System.out.println("Lenght : " + lenght);
		String name = fullCourseName.substring(16, lenght);
		System.out.println("My Course Name " + name);
		return name;
	}

	public void disableChatIcon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(chatMsg));
		driver.findElement(chatMsg).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='chatbot']")));
		driver.findElement(chatClose).click();
		driver.switchTo().defaultContent();
	}
}