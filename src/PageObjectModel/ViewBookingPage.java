package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewBookingPage {
	WebDriver driver;
	WebElement initiateWithoutEmailButton, cancelreasonSelect, cancelreasonText, cancelWithoutMailButton, wfmActionDropdown, initiateWithEmailButton ;
	WebDriverWait wait;
	
	public ViewBookingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}
	
	//Finding element initializeBookingRequestButton
	@FindBy(id="initiate_booking_request")
	WebElement initializeButton;
	
	//Finding more action dropdown
	@FindBy(xpath="//div[@id='me-info-header']/div[@class='jobs-header label-default']//div[@class='header-btn-wrap']/button[@class='btn btn-grey dropdown-toggle']")
	WebElement moreActionDropdown;
	
	/*Function to initialize booking
	*/
	public void initializeBooking() throws InterruptedException {
		
		initializeButton.click();
		System.out.println("Selecting the initialize booking menu option");
		Thread.sleep(8000);

		//selecting initialize without customer email option
		initiateWithEmailButton = driver.findElement(By.id("initiate-request-email_save"));
		
		/*Actions act = new Actions(driver);
		act.moveToElement(initiateWithEmailButton).click().build().perform();*/
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[text()='Initiate Booking Request']")));
		
		initiateWithEmailButton.click();
		System.out.println("Selecting the initialize with email option");
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.id("preloader"))));
		
	}
	
	/*Function to cancel booking
	*/
	public void cancelBooking(String cancelreason) throws InterruptedException {
		moreActionDropdown.click();
		Thread.sleep(3000);
		
		//selecting user provided cancellation reason
		cancelreasonSelect = driver.findElement(By.id("select2-cancel_reason-container"));
		cancelreasonSelect.click();
		cancelreasonText = driver.findElement(By.xpath("//body[@id='top-scroll']//span[@class='select2-dropdown select2-dropdown--above']//input[@role='textbox']"));
		cancelreasonText.sendKeys("cancelReason");
		cancelreasonText.sendKeys(Keys.ENTER);
		
		//selecting cancel without customer email option
		cancelWithoutMailButton = driver.findElement(By.id("cancel-no-email_save"));
		cancelWithoutMailButton.click();
	}
	
	/*Function to edit booking
	*/
	public void editBooking() throws InterruptedException {
		moreActionDropdown.click();
		Thread.sleep(3000);	
	}
	
	/*Function to reject booking
	*/
	public void rejectBooking(String rejectReason) throws InterruptedException {
		moreActionDropdown.click();
		Thread.sleep(3000);	
	}
	
	/*Function to check the confirmed FL name and booking status
	 */
	public Boolean checkConfirmedEditor () {
		
		Boolean result = driver.findElements(By.xpath("//div[@id='me-info-header']/div[@class='jobs-header label-success']/div[1]//span[@data='label-success']")).size() != 0;
		
		return result;
	}

	public String getBookingTitle() {
		String bookingTitle = driver.findElement(By.xpath("//div[@id='me-info-header']/div[@class='jobs-header label-default']/div[1]/div[1]//span[@class='enq-code-copy']")).getText();
		return bookingTitle.trim();
	}
	
	public String getBookingService() {
		String serviceName = driver.findElement(By.xpath("//div[@id='bme']/div[4]/div[@class='col-md-12']/div[@class='box']//div[@class='row']/div[1]/div[@class='form-group']/div[@class='label-text']")).getText();
		return serviceName;
	}
	
	public String getBookingSubjectArea() {
		String subjectArea = driver.findElement(By.xpath("//div[@id='bme']/div[4]/div[@class='col-md-12']/div[@class='box']//div[@class='row']/div[2]/div[@class='form-group']/div[@class='label-text']")).getText();
		return subjectArea;
	}
	
	public String getBookingWordCount() {
		String wordCount = driver.findElement(By.xpath("//div[@id='bme']/div[4]/div[@class='col-md-12']/div[@class='box']//div[@class='row']/div[3]/div[@class='form-group']/div[@class='label-text']")).getText();
		return wordCount;
	}
	
	public String getEnquiryTitle() {
		String enquiryTitle = driver.findElement(By.xpath("//div[@id='me-info-header']/div[@class='jobs-header label-default']/div[2]//ul[@class='header-row-bot']//span[.='Enquiry:']//following-sibling::a")).getText();
		return enquiryTitle;
	}
	
	public String getBookingStatus() {
		String bookingStatus = driver.findElement(By.xpath("//span[contains(@class,'jobs-header-status')]")).getText();
		return bookingStatus;
	}
	
	public String getFLResponse(String flName) {
		String xpathFLResponse = "//a[contains(text(),'"+flName+"')]//parent::div//parent::td//following-sibling::td[1]//div";
		
		String flResponse = driver.findElement(By.xpath(xpathFLResponse)).getText();
		return flResponse.trim();
	}
}
