package com.cognizant.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
	WebDriver driver;
	WebDriverWait wait; 
	
	@FindBy(xpath="//input[contains(@placeholder,'Where')]")
	public static WebElement txtboxLocation;
	
	@FindBy(xpath="//a[@class='lskmYLHJ' and @href='/Rentals' or contains(text(),'Holiday homes')]")
	public static WebElement tabHolidayHomes;
	
	/*public HomePage(WebDriver driver)
	{ 
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);

		PageFactory.initElements(driver, this);		
	}*/
	
	@FindBy(xpath="//span[@class='_1Qo7YQ01' or contains(text(),'Holiday Homes')]")
	public static WebElement tabMoreHolidayHomes;
	
	@FindBy(xpath="//div[@class='U2O9sR7-']//a[1]")
	public static WebElement autoSuggestedLocation;
	
	@FindBy(xpath="//button[@class='_1yB-kafB']")
	public static WebElement btnMore;
	
	
	@FindBy(xpath="//a[@class='lskmYLHJ' and @href='/Cruises' or contains(text(),'Cruises')]")
	public static WebElement tabCruises;
	
	//input[contains(@class,'_3qLQ-U8m') and contains(@placeholder,'where')]
	@FindBy(xpath="//input[contains(@class,'_3qLQ-U8m') and contains(@placeholder,'where')]")
	public static WebElement txtLocation;
	
	//_3qLQ-U8m _3TADk3fX
	
	
}

/*
text box - txtbox
date picker -dtp
checkbox -chk
dropdown -drp
Radio - rdo
tab -tab
list -lst
button -btn 
 */