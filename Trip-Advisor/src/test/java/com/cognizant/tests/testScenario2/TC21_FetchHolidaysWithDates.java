/*
 * Team Name : Mind Benders
 * Test Scenario ID :TS2
 * Test Case ID :TC21
 */
package com.cognizant.tests.testScenario2;

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



public class TC21_FetchHolidaysWithDates extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";
	boolean status;
	
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
		commonFunction.setElementValue(FindRentals.txtboxLocationName, "Nairobi");
		
		testCase.log(Status.INFO, "Providing location name,check-in & check-out  dates");
		
		//choosing chechIn and checkOut date
		datePicker.ClickTomorrowCheckInDate();
		
		datePicker.ClickCheckOutDate(5);

		//click on find rental button to search for holiday homes
		commonFunction.click(FindRentals.btnfindRental);
		
	}
	public void checkDateChosen() throws IOException
	{
		String data[]=commonFunction.getChosenFilters(HolidayHomes.chosenFilters);
		status=false;
		if (data.length==1)
			status=true;
		//System.out.println(" filters :"+status+data.length);
		
		if(status)
			testCase.log(Status.PASS, "Date  are chosen");
		else
		{
			testCase.log(Status.FAIL, "Dates are not chosen");
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);
		}
		
		Assert.assertEquals(true, status);

	}
	
	
	
	public void displayDetails() throws InterruptedException, IOException 
	{
		String strHolidayHomeNames[]=new String[5];
		String strHolidayHomePricePerDay[]=new String[5]; 
		String strHolidayHomeTotalPrice[]=new String[5];
		
		//Display top 5 holiday homes based on specifications(Location,check-in,check-out dates)
		testCase.log(Status.INFO, "Displays holiday homes");
		
		status=commonFunction.getHolidayHomeNames(HolidayHomes.lstHolidayHomeNames);
		strHolidayHomeNames=commonFunction.getHolidayHomes(HolidayHomes.lstHolidayHomeNames);
		strHolidayHomePricePerDay=commonFunction.getHolidayHomes(HolidayHomes.lstHolidayHomePricePerDay);
		strHolidayHomeTotalPrice=commonFunction.getHolidayHomes(HolidayHomes.lstHolidayHomePriceTotal);
		
		ExcelUtilities.writeExcelResult(strClassName,strHolidayHomeNames,0);
		ExcelUtilities.writeExcelResult(strClassName,strHolidayHomePricePerDay,1);
		ExcelUtilities.writeExcelResult(strClassName,strHolidayHomeTotalPrice,2);
		ExcelUtilities.excelStatusReport(strClassName, status);
		
		if(status)
			testCase.log(Status.PASS, "Holiday Home details  are fetched ");
		else
		{
			testCase.log(Status.FAIL, "Holiday Home details  are  not fetched");
			ScreenShots.captureScreenShot(strClassName);
			
			testCase.addScreenCaptureFromPath(imagePath);
		}
		
	}
	
	
	@Test(groups= {"HolidayHomes"})
	public void fetchHolidayHomes() throws IOException, InterruptedException 
	{
		testCase=extentReport.createTest(strClassName+" :Fetching Holiday homes with check-in & check-out  dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		
		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		
		
		searchFor();
		
		provideDetails();
		
		//checkDateChosen();
		
		displayDetails();
		
	}
	
	

}
