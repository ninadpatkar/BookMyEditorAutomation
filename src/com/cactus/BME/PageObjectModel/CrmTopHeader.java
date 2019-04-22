package com.cactus.BME.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CrmTopHeader {
	
	WebDriver driver;
	
	public CrmTopHeader (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Finds webelement Navigation Menu
	@FindBy(linkText="Navigation")
	WebElement navigationMenu;

	//Finds webelement Add enquiry Sub-menu
	@FindBy(linkText="Add enquiry (new)")
	WebElement addEnquirySubmenu;
	
	//Finds webelement userName menu
	@FindBy(xpath="//img[@class='user-image']")
	WebElement userNameMenu;
	
	//Finds webelement logout Sub-menu
	@FindBy(linkText="Logout")
	WebElement logoutSubmenu;
	
	/*
	 * Click navigation menu
	 * */
	public void clickNavigationMenu() {
		navigationMenu.click();
	}
	
	/*
	 * Click navigation menu
	 * */
	public void clickAddEnquirySubmenu() {
		clickNavigationMenu();
		addEnquirySubmenu.click();
	}
	
	/*
	 * Click navigation menu
	 * */
	public void clickUserNameMenu() {
		userNameMenu.click();
	}
	
	/*
	 * Click navigation menu
	 * */
	public void clickLogoutSubmenu() {
		clickUserNameMenu();
		logoutSubmenu.click();
		System.out.println("Logging out from CRM");
	}
}
