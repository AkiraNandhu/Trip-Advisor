package com.cognizant.tests.testScenario6;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;

public class TC61_CruiseButtonChecking extends DriverSetup
{
	
	CommonFunction commonFunction;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";

	
	public void checkCruise() throws IOException
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		boolean status=false;
		if(HomePage.tabCruises.isDisplayed() && HomePage.tabCruises.isEnabled())
		{
			status=true;
			testCase.log(Status.PASS, "Cruise tab is accesible");

		}
		else
		{
			testCase.log(Status.FAIL, "Cruise tab is not accesible");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);
		}
		
		ExcelUtilities.excelStatusReport(strClassName, status);
		Assert.assertEquals(true,status);
		
	}
	
	@Test(groups= {"SmokeTest"})
	public void fetchCruises() throws IOException 
	{
		
		PageFactory.initElements(driver, HomePage.class);
		

		testCase=extentReport.createTest(strClassName+" :Checking Cruise button");

		commonFunction=new CommonFunction(driver);
		
		checkCruise();
		
		
	}

}
