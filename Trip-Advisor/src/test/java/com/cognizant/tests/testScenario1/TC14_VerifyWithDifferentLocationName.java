package com.cognizant.tests.testScenario1;


import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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
 * Test Scenario ID :TS11
 * Test Case ID :TC14,TC15,TC16
 */

public class TC14_VerifyWithDifferentLocationName extends DriverSetup
{
	CommonFunction commonFunction;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";
	String[] data= {"Nairobi","Chennai","chn"};
	
	@DataProvider(name="LocationName data")
	public String[] locationDataProvider()
	{
		return data;
	}
	
	
	public void provideDetails(String locationName) throws InterruptedException, IOException
	{
		//Entering the location name
		testCase.log(Status.INFO, "Entering the location name");
		//div[@id='HEADING']/h1
		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabHolidayHomes);
		
		FindRentals.txtboxLocationName.clear();
		commonFunction.setElementValue(FindRentals.txtboxLocationName, locationName);
		commonFunction.click(FindRentals.btnfindRental);
		String strLocationName=commonFunction.getElementValue(HolidayHomes.locationTitle);

		
		boolean status=false;
		if(strLocationName.contains(locationName))
		{
			status=true;
			testCase.log(Status.PASS, "Diplayed correct information based on the  search");
		}
		else 
		{
			testCase.log(Status.FAIL, "Diplayed Incorrect information");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);

		}
		
		ExcelUtilities.excelStatusReport(strClassName, status);
		Assert.assertEquals(true,status);
		driver.get(baseUrl);
	}
	
	public void displayDetails() throws InterruptedException
	{
		String strName = commonFunction.getElementValue(FindRentals.locationName);
		Assert.assertEquals(strName, "Nairobi Houses");
		
		if(strName.equalsIgnoreCase("Nairobi Houses"))
			testCase.log(Status.PASS, "Diplayed correct information based on the searched location");
		else
			testCase.log(Status.FAIL, "Diplayed Incorrect information");
		
		System.out.println("The HolidayHomes corresponding to Nairobi are displayed\n");
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		
		
	}
	
	
	
	@Test(dataProvider="LocationName data",groups= {"SmokeTest"})
	public void fetchHolidayHomes(String locationName) throws InterruptedException, IOException 
	{
		testCase=extentReport.createTest(strClassName+" :Verifying selected location details are displayed");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		provideDetails(locationName);
		
		//displayDetails();		
	}
	
	@BeforeMethod
	public void reloadWebpage()
	{
		System.out.println("url :"+baseUrl);
		driver.get(baseUrl);
	}


}
