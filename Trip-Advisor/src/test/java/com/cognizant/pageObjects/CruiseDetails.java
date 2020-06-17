package com.cognizant.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CruiseDetails
{
	
	@FindBy(xpath="//h1[@id='HEADING']")
	public static WebElement heading;
	
	
	@FindBy(xpath="//div[@class='cruises-ship-details-overview-Overview__infoSection--3taO-']")
	public static WebElement cruiseDetails;

}

