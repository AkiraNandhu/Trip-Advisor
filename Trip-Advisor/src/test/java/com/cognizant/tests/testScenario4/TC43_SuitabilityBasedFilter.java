package com.cognizant.tests.testScenario4;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class TC43_SuitabilityBasedFilter extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;
	JavascriptExecutor jse;

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
	
	public void choosingSuitability(String suitability) throws IOException 
	{
		testCase.log(Status.INFO, "Choosing Amenities filter");


		WebElement suitabilityElement=null;
		if(suitability.equalsIgnoreCase("Kid friendly"))
			suitabilityElement=HolidayHomes.chkKidSuitability;
		else if(suitability.equalsIgnoreCase("Elder access"))
			suitabilityElement=HolidayHomes.chkElderSuitability;
		else if(suitability.equalsIgnoreCase("Pet friendly"))
			suitabilityElement=HolidayHomes.chkPetSuitability;
	
		commonFunction.click(suitabilityElement);
		
		String data[]=commonFunction.getChosenFilters(HolidayHomes.chosenFilters);
		boolean status=false;
		for(int i=0;i<data.length;i++)
		{
			if(data[i].contains(suitability))
			{
				status=true;
				System.out.println("data :"+data[i]);
			}		
				
		}
		System.out.println(" Suitability :"+status);
		Assert.assertEquals(true, status);
		
		if(status)
			testCase.log(Status.PASS, "Based on the chosen Suitability holiday homes are displayed");
		else
		{
			testCase.log(Status.FAIL, "Irrelavent data displayed");
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);
		}
   
		ExcelUtilities.excelStatusReport(strClassName, status);
	}
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws InterruptedException, IOException
	{
		testCase=extentReport.createTest(strClassName+" :Check Suitability filter is applied");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		jse=(JavascriptExecutor)driver;


		searchFor();
		provideDetails();
		
		choosingSuitability("Kid friendly");
				
	}

}
