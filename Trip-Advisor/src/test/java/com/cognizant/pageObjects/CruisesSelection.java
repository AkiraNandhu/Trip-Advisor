package com.cognizant.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CruisesSelection 
{
	static WebDriver driver;
	public CruisesSelection(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(xpath="//div[@id='cruise_line_dropdown']")
	public static WebElement drpCruiseLine;
	
	@FindBy(xpath="//div[@class='input-dropdown-Dropdown__selectLabel--x-4K4']")
	public static WebElement drpCruiseShip;
	
	//button[contains(@class,'sriconym') or @text='Search']
	@FindBy(xpath="//button[contains(@class,'sriconym') or @text='Search']")
	public static WebElement btnSearch;
	

	public  static void chooseCruiseOption(String cruiseLineShip)
	{
		
		String path="//div[contains(@class,'option') and contains(text(),'"+cruiseLineShip+"')]";
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element=driver.findElement(By.xpath(path));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	
	
}
