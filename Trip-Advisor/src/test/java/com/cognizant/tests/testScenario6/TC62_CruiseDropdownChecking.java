/*
 * Team Name : Mind Benders
 * Test Scenario ID :TS6
 * Test Case ID :TC62
 */
package com.cognizant.tests.testScenario6;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.CruisesSelection;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;

public class TC62_CruiseDropdownChecking extends DriverSetup
{
	CommonFunction commonFunction;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";

	public void chooseCruise()
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		
		
	}
	
	public void CheckCruiseLineAndShip() throws IOException
	{
		boolean status=false;
		
		if(CruisesSelection.drpCruiseLine.isDisplayed() && CruisesSelection.drpCruiseLine.isEnabled())
		{
			
				commonFunction.click(CruisesSelection.drpCruiseLine);
				CruisesSelection.chooseCruiseOption("APT");
				if(CruisesSelection.drpCruiseShip.isDisplayed() && CruisesSelection.drpCruiseShip.isEnabled())
				{
					status=true;
					testCase.log(Status.PASS, "Cruise Line and Ship is accesible");

				}
				else
				{
					testCase.log(Status.FAIL, "Cruise Ship is not accesible after selecting Cruise LIne");
					ScreenShots.captureScreenShot(strClassName);
					
					testCase.addScreenCaptureFromPath(imagePath);
					
				}

		}
		else
		{
			testCase.log(Status.FAIL, "Cruise Line tab is not accesible");
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
		PageFactory.initElements(driver, CruisesSelection.class);

		testCase=extentReport.createTest(strClassName+" :Checking CruiseLine & CruiseShip");

		commonFunction=new CommonFunction(driver);
		
		chooseCruise();
		CheckCruiseLineAndShip();
		
		
	}

}
