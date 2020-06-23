/*
 * Team Name : Mind Benders
 * This file has a common functions used in a project 
 * This achieve code re-useability
 */
package com.cognizant.businessFunctionality;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.pageObjects.HolidayHomes;

public class CommonFunction 
{
	public WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;
	
	public CommonFunction(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 30);
		jse=(JavascriptExecutor)driver;

	}
	
	//set the webelement value
	public void setElementValue(WebElement webElement,String value)
	{
		webElement.sendKeys(value);		
	}
	
	
	//Gets the webelement value and return the string
	public String getElementValue(WebElement webElement)
	{
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement.getText();	
	}
	
	//Clicks the webElement
	public void click(WebElement webElement)
	{
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	//Get the size of the webelement 
	public int getListsize(List<WebElement> webelement) 
	{
		
		return webelement.size();
	}
	
	
	
	//Get the element value stored in a list of webelement
	public String[] getHolidayHomes(List<WebElement> webelement)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(webelement));
		String data[]=new String[5];
		
		//Top 5 Holiday Homes based on your wish";
		for(int i=0;i<5;i++)
		{
			
			data[i]=webelement.get(i).getText();
		}
		return data;	
		
	}
	
	//check for element enable
	public Boolean checkElement(WebElement webElement) {
		Boolean check=webElement.isEnabled();
		return check;
		
	}
	
	//Get the chosen filters data
	public  String[] getChosenFilters(List<WebElement> webelement)
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", HolidayHomes.filters);
		wait.until(ExpectedConditions.visibilityOfAllElements(webelement));
	
		int size=webelement.size();
		String filtersData[]=new String[size];
		for(int i=0;i<size;i++)
		{
			filtersData[i]=webelement.get(i).getText();
			
			System.out.println(i+" :"+webelement.get(i).getText());
		}
		
		return filtersData;
		
	}
	
	//Get the element value stored in a list of webelement 
	public boolean getHolidayHomeNames(List<WebElement> webelement)
	{
		boolean status=false;
		
		//"Top 5 Holiday Homes based on your wish";
		for(int i=0;i<5;i++)
		{
			
			webelement.get(i).getText();
			status=true;
		}
		return status;
		
	}
	
	//check for guest count in result
	public boolean checkingGuest(List<WebElement> element,int noOfGuest)
	{
		System.out.println("Top 5 Holiday Homes based on your wish");
		wait.until(ExpectedConditions.visibilityOfAllElements(HolidayHomes.lstHolidayHomeNames));
		String actualSleeps;
		boolean status=false;
		for(int i=0;i<5;i++)
		{
			
			String actualText=element.get(i).getText();
			actualSleeps=actualText.substring(actualText.length() -1);
			int noOfSleeps=(Integer.parseInt(actualSleeps));
			if(noOfGuest<=noOfSleeps)
			{
				status=true;
				System.out.println(getElementValue(HolidayHomes.lstHolidayHomeNames.get(i)));
				
			}
			else
				break;
			
		}
		return status;
		
	}

}
