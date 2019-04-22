package com.cactus.BME.PageObjectModel;

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
	WebElement respondButton, acceptBookingOption, closeEarningDashboardButton, rejectBookingOption, tentativeAcceptBookingOption, rejectReasonNotAvailable, rejectReasonSubmit, tentativeBookingOption, tentativeReasonOther, tentativeReasonOtherText, tentativeReasonSubmit;
	
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
	 * Function to accept given booking from the list
	*/
	public void acceptBooking(String bookingTitle) throws InterruptedException {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-1")));
		
		wait.until(ExpectedConditions.elementToBeClickable(availableAssignmentsTab));
		clickAvailableAssignmentsTab();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		String xpathRespondButton = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[contains(text(),'Respond')]";
		
		//table[@id='collapsableTable']/tbody/tr[2]/td[7]//button[@class='btn btn-secondary dropdown-toggle']
		respondButton = driver.findElement(By.xpath(xpathRespondButton));
		respondButton.click();
		System.out.println("Selecting the Respond option for the booking :"+bookingTitle);

		Thread.sleep(2000);
		
		acceptBookingOption = driver.findElement(By.linkText("Accept request"));
		acceptBookingOption.click();
		System.out.println("Selecting the accept booking option");
		System.out.println("Booking "+bookingTitle+" accepted from FL");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
	}
	
	/*
	 * Function to reject given booking from the list
	*/
	public void rejectBooking(String bookingTitle) throws InterruptedException {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-1")));
		
		wait.until(ExpectedConditions.elementToBeClickable(availableAssignmentsTab));
		clickAvailableAssignmentsTab();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		String xpathRespondButton = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[contains(text(),'Respond')]";
		
		//table[@id='collapsableTable']/tbody/tr[2]/td[7]//button[@class='btn btn-secondary dropdown-toggle']
		respondButton = driver.findElement(By.xpath(xpathRespondButton));
		respondButton.click();
		System.out.println("Selecting the Respond option for the booking :"+bookingTitle);

		Thread.sleep(2000);
		
		rejectBookingOption = driver.findElement(By.linkText("Reject request"));
		rejectBookingOption.click();
		System.out.println("Selecting the reject booking option");
		
		Thread.sleep(2000);
		
		//selecting first rejet reason 'Not available'
		String xpathRejectReason = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//input[@value='83718']";
		rejectReasonNotAvailable = driver.findElement(By.xpath(xpathRejectReason));
		rejectReasonNotAvailable.click();
		System.out.println("Selecting the reject reason as Not Available");
		
		//selecting first rejet reason 'Not available'
		String xpathSubmitRejectReason = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[@action-name='workerRejectBooking']";
		rejectReasonSubmit = driver.findElement(By.xpath(xpathSubmitRejectReason));
		rejectReasonSubmit.click();
		System.out.println("Selecting the save reject reason button");
		System.out.println("Booking "+bookingTitle+" rejected from FL");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
	}
	
	/*
	 * Function to tentatively accept given booking from the list
	*/
	public void tentativeAcceptBooking(String bookingTitle) throws InterruptedException {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-1")));
		
		wait.until(ExpectedConditions.elementToBeClickable(availableAssignmentsTab));
		clickAvailableAssignmentsTab();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		String xpathRespondButton = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[contains(text(),'Respond')]";
		
		//table[@id='collapsableTable']/tbody/tr[2]/td[7]//button[@class='btn btn-secondary dropdown-toggle']
		respondButton = driver.findElement(By.xpath(xpathRespondButton));
		respondButton.click();
		System.out.println("Selecting the Respond option for the booking :"+bookingTitle);

		Thread.sleep(2000);
		
		tentativeBookingOption = driver.findElement(By.linkText("Need more time"));
		tentativeBookingOption.click();
		System.out.println("Selecting the tentatively accept booking option");
		
		Thread.sleep(2000);
		
		//selecting tentative reason 'Other'
		String xpathtentativeReason = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//input[@value='83717']";
		tentativeReasonOther = driver.findElement(By.xpath(xpathtentativeReason));
		tentativeReasonOther.click();
		
		String xpathtentativeReasonOtherText = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//input[@class='form-control reject-input tentative-reason-other-conditional']";
		tentativeReasonOtherText = driver.findElement(By.xpath(xpathtentativeReasonOtherText));
		tentativeReasonOtherText.sendKeys("Test Reason for Tentative accept");
		
		System.out.println("Selecting the tentative reason as Other");
		
		//selecting first rejet reason 'Not available'
		String xpathSubmittentativeReason = "//span[text()='"+bookingTitle+"']//parent::td//following-sibling::td[6]//button[@action-name='workerTentativeBooking']";
		tentativeReasonSubmit = driver.findElement(By.xpath(xpathSubmittentativeReason));
		tentativeReasonSubmit.click();
		System.out.println("Selecting the save tentative accept reason button");
		System.out.println("Booking "+bookingTitle+" tentatively accepted from FL");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
	}
	
	/*
	 * Function to close the earnings dashboard displayed on login to FL Dashboard
	*/
	public void closeEarningsDashboard() {
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
			
		//driver.navigate().refresh();
		
		
		closeEarningDashboardButton = driver.findElement(By.xpath("//div[@id='drilldown']/div[@class='content-wrapper']/section/div[1]/div[1]/div[@class='dash-header-wrap']/div[@class='col-md-3']/div[2]//button[@id='earning_dashboard_close']"));
		closeEarningDashboardButton.click();
		System.out.println("Closing the earnings dashboard on FL dashboard screen");
	}
	
	
}
