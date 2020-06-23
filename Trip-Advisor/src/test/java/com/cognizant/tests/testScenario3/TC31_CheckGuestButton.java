/*
 * Team Name : Mind Benders
 * Test Scenario ID :TS3
 * Test Case ID :TC31
 */
package com.cognizant.tests.testScenario3;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;

public class TC31_CheckGuestButton extends DriverSetup 
{
	CommonFunction commonFunction;
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
		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	public void checkCount() throws IOException 
	{
		testCase.log(Status.INFO, "Checking Guestcount button");

		commonFunction.click(HolidayHomes.txtGuest);
		boolean status=commonFunction.checkElement(HolidayHomes.btnGuestCount);
		if(status)
			testCase.log(Status.PASS, "Guest Count Button is accessibile");
		else
		{
			testCase.log(Status.FAIL, "Guest Count Button is  not accessibile");
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);
		}
		ExcelUtilities.excelStatusReport(strClassName, status);
	}
	
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws IOException
	{
		testCase=extentReport.createTest(strClassName+" :Checking Guest Count button");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		searchFor();
		provideDetails();
		checkCount();
				
	}


}
