/*
 * Team Name : Mind Benders
 * Test Scenario ID :TS6
 * Test Case ID :TC64
 */
package com.cognizant.tests.testScenario6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.businessFunctionality.CommonFunction;
import com.cognizant.pageObjects.CruiseDetails;
import com.cognizant.pageObjects.CruisesSelection;
import com.cognizant.pageObjects.HomePage;
import com.cognizant.utilities.DriverSetup;
import com.cognizant.utilities.ExcelUtilities;
import com.cognizant.utilities.ScreenShots;

public class TC64_FetchCruiseDetails extends DriverSetup
{
	CommonFunction commonFunction;
	ArrayList<String> tabs;
	
	String strClassName=this.getClass().getSimpleName();
	String imagePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+strClassName+".png";
	
	String result[]=new String[2];
	int size=0;

	
	@DataProvider(name="Cruise data")
	public Object[][] getExcelData() throws FileNotFoundException, IOException
	{
		Object[][] data=ExcelUtilities.getExcelData("Cruise_Data");

		ExcelUtilities.excelStatusReport(strClassName,true);

		return data;
	}
	
	public void searchFor()
	{
		//Choosing Cruise option
		testCase.log(Status.INFO, "Clicking Cruises");

		commonFunction.click(HomePage.txtboxLocation);
		commonFunction.click(HomePage.tabCruises);
		
	}
	
	public void chooseCruises(String cruiseLine,String cruiseShip) throws IOException
	{
		boolean status=false;
		testCase.log(Status.INFO, "Choosing Cruise Line and Ship");

		System.out.println("hi 1");
		commonFunction.click(CruisesSelection.drpCruiseLine);
		
		System.out.println("hi 2");
		CruisesSelection.chooseCruiseOption(cruiseLine);
		
		commonFunction.click(CruisesSelection.drpCruiseShip);
		CruisesSelection.chooseCruiseOption(cruiseShip);
		
		testCase.log(Status.INFO, "Clicking Search button and started searching");

		commonFunction.click(CruisesSelection.btnSearch);
		
		testCase.log(Status.INFO, "Verifying Title");

		tabs=new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(tabs.get(1));
		
		String title=commonFunction.getElementValue(CruiseDetails.heading);
		
		//System.out.println(title);
		
		if(title.contains(cruiseShip))
		{
			status=true;
			testCase.log(Status.PASS, "Navigated to correct cruise ship details Page");
		}

		else
		{
			testCase.log(Status.FAIL, "Irrelevant data shown");

			ScreenShots.captureScreenShot(strClassName);
						
			testCase.addScreenCaptureFromPath(imagePath);
		}
			
		
		ExcelUtilities.excelStatusReport(strClassName, status);
		
	}
	
	public void displayDetails()
	{
		int indexColumm=0;
		
		testCase.log(Status.INFO, "Display Cruise Details");
		System.out.println("Display Cruise details");

		System.out.println(commonFunction.getElementValue(CruiseDetails.cruiseDetails)); 
		
		result[size]=commonFunction.getElementValue(CruiseDetails.cruiseDetails);
		System.out.println(size+" :"+result[size]);
		
		size++;
		testCase.log(Status.PASS, "Displayed Cruise Details");
		ExcelUtilities.writeExcelResult(strClassName, result,indexColumm);
		reloadWebpage();
		
		
		
	}


	@Test(dataProvider="Cruise data",groups= {"Cruise"})
	public void fetchCruises(String cruiseLine,String cruiseShip) throws IOException 
	{
		
		System.out.println("Fetching Holiday homes without dates");
		PageFactory.initElements(driver, HomePage.class);
		PageFactory.initElements(driver, CruisesSelection.class);
		PageFactory.initElements(driver, CruiseDetails.class);

		testCase=extentReport.createTest(strClassName+" :Fetching Cruise details");

		commonFunction=new CommonFunction(driver);
		
		
		searchFor();
		
		chooseCruises(cruiseLine,cruiseShip);
		
		displayDetails();
		
	}
	
	public void reloadWebpage()
	{
		driver.close();
		driver.switchTo().window(tabs.get(0));
		driver.get(baseUrl);
		
	}
} 
