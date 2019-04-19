package TestCases;

import org.testng.annotations.Test;
import PageObjectModel.AddNewEnquiryPage;
import PageObjectModel.CreateBookingPage;
import PageObjectModel.CrmLoginPage;
import PageObjectModel.CrmTopHeader;
import PageObjectModel.FLDashboardPage;
import PageObjectModel.InquiryPage;
import PageObjectModel.ViewBookingPage;
import Utilities.BMETestData;
import Utilities.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


public class CreateBookingAndTentativeAcceptTest {
	WebDriver driver;
	
	//declared wait object for explicit wait
	WebDriverWait wait;
	
	//Declaring Page Object class object for reference pages
	CrmLoginPage pg_Login;
	CrmTopHeader pg_TopHeader;
	AddNewEnquiryPage pg_addINQ;
	InquiryPage pg_INQ;
	CreateBookingPage pg_createBooking;
	ViewBookingPage pg_viewBooking;
	FLDashboardPage pg_flDashboard;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
	LocalDateTime now = LocalDateTime.now();  
	
	
	//initilizing soft assert object
	SoftAssert sa = new SoftAssert();
	
	/*
	  Test case creates an INQ, creates and initializes a booking, assigns to one FL, and tentatively accepts from the FL Dashboard
	  @params input parameters for INQ, Booking, login credentials for CM and FL
	*/
	@Test(dataProvider="inputsBME", dataProviderClass=BMETestData.class, priority=3)
	public void createBookingTentativeAccept(String login_email, String login_passwd, String email, String service, String document, String subject, String enquiry, String units, String title, String job_date, String editor, String login_email_fl, String login_passwd_fl ) throws InterruptedException {
		
		//Open CRM url
		driver.get(Constants.CRM_URL);
		
		//Login to CRM with email and password
		pg_Login.login(login_email, login_passwd);
		
		//Waiting until the loader is gone.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html//div[@id='preloader']")));
		
		//get parent window handle.
		String parentHandle = driver.getWindowHandle();
		
		//create a new INQ
		pg_TopHeader.clickAddEnquirySubmenu();
		
		//Get all current window handles
		Set<String> handles = driver.getWindowHandles();
		
		//Switching to the 'add new enquiry window'
		for(String handle : handles) {
			if(!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(5000);
				break;
			}
		}
		
		//Submit a new enquiry
		String enquiry_title = pg_addINQ.createNewINQ(email, service, document, subject, enquiry, units);
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("INQ"));
		
		//create a new booking
		pg_INQ.clickCreateBooking();
		
		//closing the current window
		driver.close();
		
		//Get all current window handles
		handles = driver.getWindowHandles();
				
		//Switching to the 'add new booking window'
		for(String handle : handles) {
			if(!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(5000);
				break;
			}
		}
		
		//adding current + 1 hour to the date fields
		job_date = dtf.format(now.plusDays(1));
		
		//submit the booking form
		pg_createBooking.submitBookingForm(title, units, job_date, job_date, job_date, job_date, editor);
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("EB"));
		
		//Fetching details of the actual booking created
		String bookingTitle = pg_viewBooking.getBookingTitle();
		String enquiryTitleBookingLink = pg_viewBooking.getEnquiryTitle();
		String serviceBooking = pg_viewBooking.getBookingService();
		String subjectBooking = pg_viewBooking.getBookingSubjectArea();
		String bookingStatus = pg_viewBooking.getBookingStatus();
		
		//Assertions on the booking details.
		sa.assertEquals(enquiry_title, enquiryTitleBookingLink);
		sa.assertEquals(service, serviceBooking);
		sa.assertEquals(subject, subjectBooking);
		sa.assertEquals(bookingStatus, "NEW");
		
		System.out.println("Booking with title "+bookingTitle+" is in status : "+bookingStatus);
		
		//initialize the booking
		pg_viewBooking.initializeBooking();
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("EB"));
		
		//fetching status of the booking
		bookingStatus = pg_viewBooking.getBookingStatus();
		
		//Assert on the status of the booking
		sa.assertEquals(bookingStatus, "PENDING");
		
		System.out.println("Booking with title "+bookingTitle+" is in status : "+bookingStatus);
		
		//Logout from CM Dashboard
		pg_TopHeader.clickLogoutSubmenu();
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("Sign in to your account"));
		
		//Login to CRM with email and password
		pg_Login.login(login_email_fl, login_passwd_fl);
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("Dashboard  |  CACTUS CRM"));
		
		//closing the earnings dashboard
		pg_flDashboard.closeEarningsDashboard();
		
		//accepting the booking request
		pg_flDashboard.tentativeAcceptBooking(bookingTitle);
		
		//logout from FL Dashboard
		pg_TopHeader.clickLogoutSubmenu();
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("Sign in to your account"));
		
		//Login to CRM with email and password
		pg_Login.login(login_email, login_passwd);
				
		driver.navigate().to(Constants.Booking_URL+bookingTitle);
		
		//Waiting until the page is loaded.
		wait.until(ExpectedConditions.titleContains("EB"));
		
		//fetching status of the booking
		bookingStatus = pg_viewBooking.getBookingStatus();
		
		//Assert on the status of the booking
		sa.assertEquals(bookingStatus, "PENDING");
		
		System.out.println("Booking with title "+bookingTitle+" is in status : "+bookingStatus);
		
		//fetching status of the fl response
		String flResponseStatus = pg_viewBooking.getFLResponse(editor);
						
		//Assert on the status of the fl response
		sa.assertEquals(flResponseStatus, "Tentatively accepted");
		
		System.out.println("FL "+editor+" response for booking "+bookingTitle+" is : "+flResponseStatus);
		
		//logout from FL Dashboard
		pg_TopHeader.clickLogoutSubmenu();
		
		sa.assertAll();
	}
	
	@BeforeMethod
	public void testSetup() throws Exception {
	  
		//set system property for chrome driver
		System.setProperty(Constants.ChromeDriver_property, Constants.ChromeDriver_path);

		//initialize driver object with Chrome driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Initializing object of Page bobject models
		pg_Login = new CrmLoginPage(driver);
		pg_TopHeader = new CrmTopHeader(driver);
		pg_addINQ = new AddNewEnquiryPage(driver);
		pg_INQ = new InquiryPage(driver);
		pg_createBooking = new CreateBookingPage(driver);
		pg_viewBooking = new ViewBookingPage(driver);
		pg_flDashboard = new FLDashboardPage(driver);
		
		//initializing explicit wait object
		wait = new WebDriverWait(driver, 60);

	}

	@AfterMethod
	public void testClose() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}	
}
