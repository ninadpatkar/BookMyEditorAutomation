package com.cactus.BME.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

//https://crm.cactusglobal.com/enquiry/view/INQ_TABXQ_10
public class InquiryPage {
	WebDriver driver;
	WebDriverWait wait;
	WebElement createBookingOption;
	
	public InquiryPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Finding webelement More menu
	@FindBy(xpath="/html//div[@id='me-info-header']/div[1]//button[@class='btn btn-grey dropdown-toggle']")
	WebElement moreMenu;
	
	public void clickCreateBooking() {
		moreMenu.click();
		createBookingOption = driver.findElement(By.id("create_booking"));
		createBookingOption.click();
		System.out.println("Select create booking option on INQ page");
	}
	
}
