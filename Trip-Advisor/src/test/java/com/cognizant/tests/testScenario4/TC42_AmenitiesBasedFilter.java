package com.cognizant.tests.testScenario4;


import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.businessFunctionality.DatePicker;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;

public class TC42_AmenitiesBasedFilter extends DriverSetup
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
	
	public void choosingAmenities(String amenities) throws IOException 
	{
		testCase.log(Status.INFO, "Choosing Amenities filter");

		WebElement amenitiesElement=null;
		if(amenities.equalsIgnoreCase("Wi-fi"))
			amenitiesElement=HolidayHomes.chkWifiAmenities;
		else if(amenities.equalsIgnoreCase("Internet"))
			amenitiesElement=HolidayHomes.chkInternetAmenities;
		else if(amenities.equalsIgnoreCase("Elevator/Lift access"))
		{
			WebElement element=driver.findElement(By.className("_3ncH7U-p"));
			jse.executeScript("arguments[0].scrollIntoView();",element);
			System.out.println("hi........");
			commonFunction.click(HolidayHomes.btnShowMore);
			amenitiesElement=HolidayHomes.chkLiftAmenities;
		}
		
		commonFunction.click(amenitiesElement);
		
		//jse.executeScript("arguments[0].scrollIntoView();",HolidayHomes.chosenFilters);
		String data[]=commonFunction.getChosenFilters(HolidayHomes.chosenFilters);
		boolean status=false;
		for(int i=0;i<data.length;i++)
		{
			if(data[i].contains(amenities))
			{
				status=true;
				System.out.println("data :"+data[i]);
			}		
				
		}
		System.out.println(" AMenities :"+status);
		Assert.assertEquals(true, status);
		
		if(status)
			testCase.log(Status.PASS, "Based on the chosen amenities holiday homes are displayed");
		else
		{
			testCase.log(Status.FAIL, "Irrelavent data displayed");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);
		}
				

		ExcelUtilities.excelStatusReport(strClassName, status);
   
	}
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws IOException 
	{
		testCase=extentReport.createTest(strClassName+" :Check for amenities are selected ");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		jse=(JavascriptExecutor)driver;


		searchFor();
		provideDetails();
		
		choosingAmenities("Internet");
		
				
	}


}
