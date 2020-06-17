package com.cognizant.tests.testScenario1;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.FindRentals;
import com.cognizant.pageObjects.HolidayHomes;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;



public class TC13_SearchBarHolidayHomes extends DriverSetup
{
	CommonFunction commonFunction;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";
	
	public void searchFor()
	{
		//Choosing holiday homes option
		testCase.log(Status.INFO, "Selecting holiday homes");

		commonFunction.click(HomePage.txtboxLocation);
		
		commonFunction.click(HomePage.tabHolidayHomes);
	}

	public void checkHolidayHome() throws IOException
	{
		boolean status=false;
		String strActualTitle=commonFunction.getElementValue(FindRentals.title);
		System.out.println(strActualTitle);
		if(strActualTitle.contains("holiday rental"))
		{
			status=true;
			testCase.log(Status.PASS, "Navigated correctly to holiday homes page");

		}
		else
		{
			testCase.log(Status.FAIL, "Mismatched page is dispayed");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);

		}
		
		ExcelUtilities.excelStatusReport(strClassName, status);
		
		Assert.assertEquals(true, status);

			
	}
	
	@Test(groups= {"SmokeTest"})
	public void fetchHolidayHomes() throws IOException 
	{
		testCase=extentReport.createTest(strClassName+" :Verifying holiady homes via search bar");

		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, FindRentals.class);
		PageFactory.initElements(driver, HolidayHomes.class);

		commonFunction=new CommonFunction(driver);
		searchFor();
		checkHolidayHome();		
	}

}
