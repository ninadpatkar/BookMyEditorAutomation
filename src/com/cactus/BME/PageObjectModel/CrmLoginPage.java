package com.cactus.BME.PageObjectModel;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrmLoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CrmLoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}
	
	//Finds webelement emailId text box
	@FindBy(id="email_id")
	WebElement emailIdTextBox;
	
	//Finds webelement password text box
	@FindBy(id="password")
	WebElement passwordTextBox;
	
	//Finds webelement signIn button
	@FindBy(xpath="/html//form[@id='login_form']//div[@class='col-lg-12']/button[@type='submit']")
	WebElement signInButton;
	
	//Finds webelement forgot password link
	@FindBy(linkText="Forgot password")
	WebElement forgotPasswordLink;
	
		
	/*
	 * Click forgot password link
	 * */
	public void clickForgtPasswordLink() {
		forgotPasswordLink.click();
	}
	
	/*
	 * login to CRM, enter emailId and Password, and click signIn
	 * @params : emailId, password
	 * */
	public void login(String emailId, String password) {
		emailIdTextBox.sendKeys(emailId);
		passwordTextBox.sendKeys(password);
		signInButton.click();
		System.out.println("Logging in with user :"+emailId);
		
		//Waiting until the loader is gone.
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html//div[@id='preloader']")));
	}

}
