package com.cognizant.tests.testScenario4;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class TC44_ClearFilters extends DriverSetup
{
	CommonFunction commonFunction;
	DatePicker datePicker;
	JavascriptExecutor jse;
	WebDriverWait wait;
	
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
	
	public void clearFilters() throws IOException 
	{
		testCase.log(Status.INFO, "Clearing chosen filters");
		commonFunction.click(HolidayHomes.chkKidSuitability);

		wait.until(ExpectedConditions.elementToBeClickable(HolidayHomes.btnClearfilters));
		commonFunction.click(HolidayHomes.btnClearfilters); 
		
		String data[]=commonFunction.getChosenFilters(HolidayHomes.chosenFilters);
		boolean status=false;
		if (data.length==0)
			status=true;
		System.out.println(" Suitability :"+status+data.length);
		Assert.assertEquals(true, status);
		
		if(status)
			testCase.log(Status.PASS, "Filters are cleared");
		else
		{
			testCase.log(Status.FAIL, "Filters are not cleared");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);
		}
		
		ExcelUtilities.excelStatusReport(strClassName, status);
	}
	
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws InterruptedException, IOException
	{
		testCase=extentReport.createTest(strClassName+" :Checking Sortby Filter option");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);
		

		commonFunction=new CommonFunction(driver);
		datePicker=new DatePicker(driver);
		jse=(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, 30);



		searchFor();
		provideDetails();
		clearFilters();
		
		
				
	}

}
