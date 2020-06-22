package com.cognizant.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HolidayHomes
{
	WebDriver driver;
	
	@FindBy(xpath="//div[contains(text(),'Suitability')]//following::div[contains(text(),'Kid friendly')]")
	public static WebElement chkKidSuitability;
	
	@FindBy(xpath="//div[contains(text(),'Suitability')]//following::div[contains(text(),'Pet friendly')]")
	public static WebElement chkPetSuitability;
	
	@FindBy(xpath="//div[contains(text(),'Suitability')]//following::div[contains(text(),'Elder access')]")
	public static WebElement chkElderSuitability;
	
	@FindBy(xpath="//div[contains(text(),'Amenities')]//following::div[contains(text(),'Wi-Fi')]")
	public static WebElement chkWifiAmenities;
	
	@FindBy(xpath="//div[contains(text(),'Amenities')]//following::div[contains(text(),'Internet')]")
	public static WebElement chkInternetAmenities;
	
	@FindBy(xpath="//div[contains(text(),'Amenities')]//following::div[contains(text(),'Elevator/Lift access')]")
	public static WebElement chkLiftAmenities;

	
	//div[contains(@class,'_3kI1z_wP v8kb8R34')]/span[contains(text(),'Show more')]
	//*[@id="component_2"]/div/div[2]/div/div[1]/div[1]/div/div/div[9]/div[6]/span[1]
	@FindBy(xpath="//*[@id=\"component_2\"]/div/div[2]/div/div[1]/div[1]/div/div/div[9]/div[6]/span[1]")
	public static WebElement btnShowMore;
	
	
	@FindBy(xpath="//span[contains(text(),'Tripadvisor Sort') or @class='ui_tag_box sort-summary']")
	public static WebElement btnSortby;
	
	@FindBy(xpath="//span[contains(text(),'Traveller Rating')]")
	public static WebElement rdoTravellerRating;
	
	
	
	@FindBy(xpath="//*[contains(@class,'vr-srp-propertyCard-pieces-PropertyTitleSection__propertyTitle--351Sr')]")
	public static List<WebElement> lstHolidayHomeNames;
	
	
	@FindBy(xpath="//div[contains(text(),'per night') and contains(@class,'vr-srp-propertyCard-pieces-Commerce__pricePeriodText--3Ix4g')]/preceding::div[1]")
	public static List<WebElement> lstHolidayHomePricePerDay;
	
	@FindBy(xpath="//div[contains(text(),'nights')]")
	public static List<WebElement> lstHolidayHomePriceTotal;
	
	
	
	@FindBy(xpath="//li[@class='yJ4mfBYR']/div")
	public static List<WebElement> chosenFilters;
	
	@FindBy(xpath="//li[@class='yJ4mfBYR'][1]")
	public static WebElement filters;
	
	//a[@class='vr-search-results-page-root-TierOneMessaging__clearAllText--3zuNy' or contains(text(),'Clear all filters')]
	
	@FindBy(xpath="//a[@class='vr-search-results-page-root-TierOneMessaging__clearAllText--3zuNy' or contains(text(),'Clear all filters')]")
	public static WebElement btnClearfilters;
	
	/*public HolidayHomes(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	*/
	
	@FindBy(xpath="//div[contains(text(),'Guests') or @class='vr-traveler-input-picker-GV9Jm2u7rmsCe65wKzPTw5jtS38n2tVEGibel--3u14t']")
	public static WebElement txtGuest;
	
	@FindBy(xpath="//div[contains(text(),'guests')]/following::span[@class='ui_icon plus vr-traveler-input-picker-NumberPicker__control--ztiYv'][1]")
	public static WebElement btnGuestCount;
	
	
	@FindBy(xpath="//button[contains(@text(),'Apply') or contains(@class,'ui_button primary fullwidth')]")
	public static WebElement btnApplyGuestCount;
	
	@FindBy(xpath="//div[contains(text(),'sleeps')]")
	public static List<WebElement> matchSleeps;
	
	
	@FindBy(xpath="//div[@id='HEADING']/h1[@class='page_h1_line1']")
	public static WebElement locationTitle;
	
	
	

}
