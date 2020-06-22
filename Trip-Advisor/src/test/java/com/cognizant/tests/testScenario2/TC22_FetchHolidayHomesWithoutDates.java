package com.cognizant.tests.testScenario2;

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

/*
 * Test Scenario ID :TS21
 * Test Case ID :TC22
 */


public class TC22_FetchHolidayHomesWithoutDates extends DriverSetup
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
		testCase.log(Status.INFO, "Providing location details");

		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi"); 

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	
	public void displayDetails() throws IOException 
	{
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");

		boolean status=commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
		
		if(status)
		{
			testCase.log(Status.PASS,"Displayed exact Holiday Homes details");
			
		}
		else
		{
			testCase.log(Status.FAIL,"Irrelavent data displayed");
			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);
		}
		ExcelUtilities.excelStatusReport(strClassName, status);
	}
	
	
	
	@Test(groups= {"HolidayHomes"})
	public void fetchHolidayHomes() throws IOException  
	{
		System.out.println("Fetching Holiday homes without dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		testCase=extentReport.createTest(strClassName+" :Fetching Holiday homes without dates");

		commonFunction=new CommonFunction(driver);
		
		searchFor();
		
		provideDetails();
		
		displayDetails();
		
		
	}

}
