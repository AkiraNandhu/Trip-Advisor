package com.cognizant.tests.testScenario3;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;



public class TC32_GuestCountVerification extends DriverSetup 
{
	CommonFunction commonFunction;
	DatePicker datePicker;

	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";

	
	public void searchFor()
	{
		//Choosing holidayhomes option
		testCase.log(Status.INFO, "Clicking Holiday homes");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);
	}
	
	public void provideDetails()
	{
		//entering the location name
		testCase.log(Status.INFO, "Providing location name");

		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi"); 
		
		testCase.log(Status.INFO, "Choosing check-in check-out dates");

		//Choosing check-in check-out date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(6);
		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	
	
	public void checkGuestCount(int noOfGuest) throws IOException
	{
		noOfGuest=noOfGuest-2;
		commonFunction.click(HolidayHomes.txtGuest);
		for(int i=1;i<=noOfGuest;i++)
			commonFunction.click(HolidayHomes.btnGuestCount);
		
		System.out.println(commonFunction.getElementValue(HolidayHomes.txtGuest));
	
		
		boolean status=commonFunction.checkingGuest(HolidayHomes.matchSleeps,noOfGuest);
		
		Assert.assertEquals(true, status);
		if(status)
			testCase.log(Status.PASS, "Displayed with corect guest count");
		else
		{
			testCase.log(Status.FAIL, "Mismatch data displayed");
			
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);
		}

		ExcelUtilities.excelStatusReport(strClassName, status);
		
	}

	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws InterruptedException, IOException
	{
		testCase=extentReport.createTest(strClassName+" :Checking guest count in holiday homes");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);

		searchFor();
		provideDetails();
		checkGuestCount(4);
		
				
	}

}
