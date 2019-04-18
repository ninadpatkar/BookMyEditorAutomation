package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FLDashboardPage {
	
	WebDriver driver;
	WebDriverWait wait;
	WebElement respondButton, acceptBookingOption, closeEarningDashboardButton;
	
	public FLDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		wait = new WebDriverWait(driver, 10);
	}
	
	
	//Finding webelement availableAssignmentsTab menu
	@FindBy(id="available-jobs")
	WebElement availableAssignmentsTab;
		
	//Finding webelement availableAssignmentsTab menu
	@FindBy(id="accepted-jobs")
	WebElement acceptedAssignmentsTab;
	
	/*
	 * Function to click on the available assignments tab
	*/
	public void clickAvailableAssignmentsTab() {
		availableAssignmentsTab.click();
		System.out.println("Selecting the available assignments tab on FL Dashboard");
	}

	/*
	 * Function to click on the accepted assignments tab
	*/
	public void clickAcceptedAssignmentsTab() {
		acceptedAssignmentsTab.click();
		System.out.println("Selecting the accepted assignments tab on the FL Dashboard");
	}
	
	
	/*
	 * Function to accept first booking from the list
	*/
	public void acceptBooking(String bookingTitle) throws InterruptedException {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-1")));
		
		wait.until(ExpectedConditions.elementToBeClickable(availableAssignmentsTab));
		clickAvailableAssignmentsTab();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		
		String xpathRespondButton = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[contains(text(),'Respond')]";
		
		//table[@id='collapsableTable']/tbody/tr[2]/td[7]//button[@class='btn btn-secondary dropdown-toggle']
		respondButton = driver.findElement(By.xpath(xpathRespondButton));
		respondButton.click();
		System.out.println("Selecting the Respond option for the booking :"+bookingTitle);

		Thread.sleep(2000);
		
		acceptBookingOption = driver.findElement(By.linkText("Accept request"));
		acceptBookingOption.click();
		System.out.println("Selecting the accept booking option");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
	}
	
	/*
	 * Function to reject first booking from the list
	*/
	public void rejectBooking(String bookingTitle) throws InterruptedException {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-1")));
		
		wait.until(ExpectedConditions.elementToBeClickable(availableAssignmentsTab));
		clickAvailableAssignmentsTab();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		
		String xpathRespondButton = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[contains(text(),'Respond')]";
		
		//table[@id='collapsableTable']/tbody/tr[2]/td[7]//button[@class='btn btn-secondary dropdown-toggle']
		respondButton = driver.findElement(By.xpath(xpathRespondButton));
		respondButton.click();
		System.out.println("Selecting the Respond option for the booking :"+bookingTitle);

		Thread.sleep(2000);
		
		acceptBookingOption = driver.findElement(By.linkText("Reject request"));
		acceptBookingOption.click();
		System.out.println("Selecting the reject booking option");
		
		//selecting first rejet reason 'Not available'
		
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
	}
	
	public void closeEarningsDashboard() {
		
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html//div[@id='preloader']")));
			
		//driver.navigate().refresh();
		
		
		closeEarningDashboardButton = driver.findElement(By.xpath("//div[@id='drilldown']/div[@class='content-wrapper']/section/div[1]/div[1]/div[@class='dash-header-wrap']/div[@class='col-md-3']/div[2]//button[@id='earning_dashboard_close']"));
		closeEarningDashboardButton.click();
		System.out.println("Closing the earnings dashboard on FL dashboard screen");
	}
	
	
}
