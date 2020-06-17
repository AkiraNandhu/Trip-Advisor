package com.cognizant.tests.testScenario1;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;



/*
 * Test Scenario ID :TS11
 * Test Case ID :TC11
 */

public class TC11_CheckAutoSuggestion extends DriverSetup 
{
	CommonFunction commonFunction;
	
	String strClassName=this.getClass().getSimpleName();
	
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";

	public void provideDetails() throws InterruptedException, IOException
	{
		//Entering the location name
		boolean status=false;
		
		testCase.log(Status.INFO, "Entering the location name");
		commonFunction.setElementValue(HomePage.txtboxLocation, "Nairobi"); 
		Thread.sleep(4000);

		//click on find rental button to search for holiday homes

		testCase.log(Status.INFO, "Displaying autosuggestion location name");
		commonFunction.click(HomePage.autoSuggestedLocation);
		Thread.sleep(4000);	
		String strLocationName=commonFunction.getElementValue(FindRentals.locationCheck);
		
		Assert.assertEquals(strLocationName,"Nairobi");
		
		if(strLocationName.equalsIgnoreCase("Nairobi"))
		{
			status=true;
			testCase.log(Status.PASS, "Diplayed correct information based on the auto search");
			
		}
		else
		{
			testCase.log(Status.FAIL, "Diplayed Incorrect information");
			
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);

		}
		
		ExcelUtilities.excelStatusReport(strClassName, status);
		
	}
	
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws InterruptedException, IOException 
	{
		testCase=extentReport.createTest(strClassName+" :Verifying autosuggestion location search box");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);

		commonFunction=new CommonFunction(driver);
		provideDetails();
		
	}


}