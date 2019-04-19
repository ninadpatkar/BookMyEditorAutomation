package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateBookingPage {
	WebDriver driver;
	WebElement onlyPrevEditorCheck, onlyEligibleEditorCheck, editorSearchBox, saveEditorButton, editorSelectionBox, saveBookingButton ;
	WebDriverWait wait;
	
	public CreateBookingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	//Finding webelement projectTitle
	@FindBy(id="field_paper_title")
	WebElement projectTitleTextBox;
	
	//Finding webelement wordCount
	@FindBy(id="field_fix_unit_count")
	WebElement wordCountTextBox;
	
	//Finding webelement jobStartDate
	@FindBy(id="field_expected_job_start_date")
	WebElement jobStartDateTextBox;
	
	//Finding webelement editingStartDate
	@FindBy(id="field_expected_editing_start_date")
	WebElement editingStartDateTextBox;
	
	//Finding webelement prefDeliveryDate
	@FindBy(id="field_preferred_delivery_date")
	WebElement prefDeliveryDateTextBox;
	
	//Finding webelement maxDeliveryDate
	@FindBy(id="field_max_delivery_date")
	WebElement maxDeliveryDateTextBox;
	
	//Finding webelement editorSelector
	@FindBy(id="submit_create_booking_step_1")
	WebElement editorSelectorButton;
	
	
	/*
	 * Submit create new booking form
	 * @params projectTitle, wordCount, jobStartDate, editingStartDate, preferredDeliveryDate, maximumDeliveryDate, preferredEditorName
	 * */
	public void submitBookingForm(String projectTitle, String wordCount, String jobStartDate, String editingStartDate, String prefDeliveryDate, String maxDeliveryDate, String editorNmae ) throws InterruptedException {
		
		//enter project title
		projectTitleTextBox.sendKeys(projectTitle);
		System.out.println("Entering project title for booking as :"+projectTitle);
		
		//enter wordCount
		wordCountTextBox.sendKeys(wordCount);
		System.out.println("Entering word count for booking as :"+wordCount);
		
		//enter jobStartDate
		jobStartDateTextBox.sendKeys(jobStartDate);
		System.out.println("Entering job start date as :"+jobStartDate);
		
		//enter editingStartDate
		editingStartDateTextBox.sendKeys(editingStartDate);
		System.out.println("Entering editing start date as :"+editingStartDate);
		
		//enter prefDeliveryDate
		prefDeliveryDateTextBox.sendKeys(prefDeliveryDate);
		System.out.println("Entering preferred delivery date as :"+prefDeliveryDate);
		
		//enter maxDeliveryDate
		maxDeliveryDateTextBox.sendKeys(maxDeliveryDate);
		System.out.println("Entering max delivery date as :"+maxDeliveryDate);
		
		//enter click on editor selector button
		editorSelectorButton.click();
		Thread.sleep(2000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//remove only previous filter by clicking
		onlyPrevEditorCheck = driver.findElement(By.id("previous_editor"));
		onlyPrevEditorCheck.click();
		Thread.sleep(5000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//search for the test editor
		editorSearchBox = driver.findElement(By.id("editor_worker_code"));
		editorSearchBox.sendKeys(editorNmae);
		editorSearchBox.sendKeys(Keys.ENTER);
		System.out.println("search the editor :"+editorNmae);
		Thread.sleep(5000);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		
		//select first editor from the list
		editorSelectionBox = driver.findElement(By.xpath("/html//table[@id='selectWorkersTable']/tbody/tr[1]//input[@name='worker_id[]']"));
		editorSelectionBox.click();
		System.out.println("Selecting first editor from the list");
		
		//save editor selection for booking
		saveEditorButton = driver.findElement(By.id("submit_create_booking_step_2"));
		saveEditorButton.click();
		System.out.println("Saving editor list");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
		//save booking
		saveBookingButton = driver.findElement(By.id("submit_create_booking_step_3"));
		saveBookingButton.click();
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
	}
}
