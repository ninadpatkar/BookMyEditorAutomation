package com.cactus.BME.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//https://crm.cactusglobal.com/backend/enquiry/create
public class AddNewEnquiryPage {
	WebDriver driver;
	WebDriverWait wait;
	
	WebElement seviceSelectList, serviceNameTextBox, isItaMRENo, startWorkYes, firstMREFreeNo, documentTypeSelect, documentTypeTextBox, journalformattingNo, subjectAreaTextBox, languageAmerican, skipFile, enquiryTypeSelect, enquiryTypeText, unitCountText, recommentButton, planSelectButton, paymentPrefPrevButton, submitSaveButton;
	
	public AddNewEnquiryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}
	
	//Finds webelement addINQPageTitle
	@FindBy(id="page-header")
	WebElement addINQPageTitle;
	
	//Finds webelement email
	@FindBy(id="email_id")
	WebElement emailTextBox;
	
	//Finds webelement client code
	@FindBy(id="client_code")
	WebElement clientCodeTextBox;
	
	//Finds webelement createINQ button
	@FindBy(id="step-2-1")
	WebElement createINQButton;

	/*
	 * Enters given string in emailId field
	 * */
	public void enterEmailId(String emailId) {
		emailTextBox.sendKeys(emailId);
	}
	
	/*
	 * Enters given string in emailId field
	 * */
	public void enterClientCode(String clientCode) {
		clientCodeTextBox.sendKeys(clientCode);
	}
	
	/*
	 * Click create enquiry button
	 * */
	public void clickCreateEnquiryButton() {
		
		createINQButton.click();
	}
	
	/*
	 * Submit create new enquiry form
	 * @params emailId, serviceName, documentType, subjectArea, enquiryType, unitCount
	 * */
	public String createNewINQ(String emailId, String serviceName, String documentType, String subjectArea, String enquiryType, String unitCount) throws InterruptedException {
		//waiting for the elements to be clickabale
		wait.until(ExpectedConditions.elementToBeClickable(createINQButton));
		
		//enter client email id and click on create enquiry button
		enterEmailId(emailId);
		clickCreateEnquiryButton();
		System.out.println("Clicking on Add enquiry menu");
		Thread.sleep(5000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//enter the service name
		seviceSelectList = driver.findElement(By.xpath("/html//span[@id='select2-service_id-container']"));
		seviceSelectList.click();
		Thread.sleep(2000);
		
		serviceNameTextBox = driver.findElement(By.xpath(".//input[@class='select2-search__field']"));
		
		serviceNameTextBox.sendKeys(serviceName);
		serviceNameTextBox.sendKeys(Keys.ENTER);
		System.out.println("selecting service :"+serviceName);
		Thread.sleep(5000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//select no for is this a rework for previous job question
		isItaMRENo = driver.findElement(By.id("field_enq_ex_is_it_mre_no"));
		isItaMRENo.click();
		
		//select no for start work immediately question
		startWorkYes = driver.findElement(By.id("field_enq_ex_can_we_start_yes"));
		//((JavascriptExecutor) driver).executeScript("return arguments[0].click();", startWorkYes);
		startWorkYes.click();
		
		/*//select no for 'do you want free mre' question
		firstMREFreeNo = driver.findElement(By.id("field_enq_ex_first_mre_free_no"));
		firstMREFreeNo.click();*/
		
		//enter document type
		documentTypeSelect = driver.findElement(By.xpath("//div[@id='field_enq_ex_type_of_doc']//span[@role='combobox']/span[@title='-- Select --']"));
		documentTypeSelect.click();
		Thread.sleep(3000);
		documentTypeTextBox = driver.findElement(By.xpath(".//input[@class='select2-search__field']"));
		documentTypeTextBox.sendKeys(documentType);
		documentTypeTextBox.sendKeys(Keys.ENTER);
		System.out.println("selecting document type as :"+documentType);
		Thread.sleep(3000);
		
		//select no for 'journal formatting' question
		journalformattingNo = driver.findElement(By.id("field_enq_ex_formatng_info_no"));
		journalformattingNo.click();
		
		//enter the subject area
		subjectAreaTextBox = driver.findElement(By.id("field_subject_area"));
		subjectAreaTextBox.sendKeys(subjectArea);
		subjectAreaTextBox.sendKeys(Keys.ARROW_DOWN);
		subjectAreaTextBox.sendKeys(Keys.TAB);
		System.out.println("Selecting subject area as :"+subjectArea);
		
		//select language style as american
		languageAmerican = driver.findElement(By.id("field_language_style_american"));
		languageAmerican.click();
		
		//skip file upload option
		skipFile = driver.findElement(By.id("skip-job-file"));
		((JavascriptExecutor) driver).executeScript("return arguments[0].click();", skipFile);
		//skipFile.click();
		
		//enter the enquiry type
		enquiryTypeSelect = driver.findElement(By.id("select2-enquiry_type-container"));
		enquiryTypeSelect.click();
		enquiryTypeText = driver.findElement(By.xpath("//body[@id='top-scroll']//span[@class='select2-dropdown select2-dropdown--above']//input[@role='textbox']"));
		enquiryTypeText.sendKeys(enquiryType);
		enquiryTypeText.sendKeys(Keys.ENTER);
		System.out.println("Selecting enquiry type as :"+enquiryType);
		
		//enter the unit counts
		unitCountText = driver.findElement(By.id("job_unit_count"));
		unitCountText.sendKeys(unitCount);
		System.out.println("Entering word count as :"+unitCount);
		
		//click on plan recommender
		recommentButton = driver.findElement(By.id("recommend_tat"));
		recommentButton.click();
		Thread.sleep(5000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//click on select plan button
		planSelectButton = driver.findElement(By.id("select-tat"));
		planSelectButton.click();
		System.out.println("Selecting first plan from the recommender");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//select previously set payment preferences
		paymentPrefPrevButton = driver.findElement(By.xpath("/html//div[@id='psit-preference-buttons']/div[1]/div[@class='form-group']/button[@type='button']"));
		paymentPrefPrevButton.click();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//submit the new enquiry form
		submitSaveButton = driver.findElement(By.id("submit_enquiry"));
		submitSaveButton.click();
		System.out.println("submitting the enquiry form");
		
		//wait until enquiry page is loaded
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='me-info-header']/div[1]/div[@class='row']//span[@class='label label-default']")));
		
		//fetching the enquiry title
		String enquiry = driver.findElement(By.id("enquiry_title")).getText();
		System.out.println("New enquiry created : "+enquiry);
		return enquiry;
	}
}
