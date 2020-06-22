package com.cognizant.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class DriverSetup 
{
	public static WebDriver driver;
	public static Properties properties;
	public static String baseUrl;
	
	public static ExtentReports extentReport;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest testCase;
	public static Object[][] data;
	
	
	@BeforeSuite(groups= {"SmokeTest","HolidayHomes","Cruise"})
	@Parameters({ "browser", "environment" })
	public void driverSetup(@Optional("chrome") String browser, @Optional("local") String environment) throws IOException  
	{
		extentReport =new ExtentReports();
		htmlReporter=new ExtentHtmlReporter("ExtentReport.html");
		extentReport.attachReporter(htmlReporter);
		
		/*BasicConfigurator.configure();
		System.setProperty("org.freemarker.loggerLibrary", "none");*/

		testCase=extentReport.createTest("Automation Started");
		System.out.println("Browser :"+browser+" Environment :"+environment);
		
		if(environment.equalsIgnoreCase("grid"))
			getDriverGrid(browser);
		else
			getDriver(browser);
	

		
	} 
	
	@AfterSuite(groups= {"SmokeTest","HolidayHomes","Cruise"})
	public void closeDriverSetup()
	{
		closeDriver();
		extentReport.flush();
		
	}
	@BeforeClass(groups= {"SmokeTest","HolidayHomes","Cruise"})
 	public void startApplication() throws FileNotFoundException, IOException
	{
		//testCase.log(Status.INFO, "Launching Application :"+baseUrl);
		driver.get(baseUrl);
		
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
	}
	
	@AfterClass(groups= {"SmokeTest","HolidayHomes","Cruise"})
	public void endApplication()
	{
		//System.out.println("in after Test");
		//driver.navigate().to(baseUrl);
	}
	
	//Loads properties from configuration file
	public static Properties loadPropertyFile() throws IOException
	{
		FileInputStream fileInputStream=new FileInputStream("config.properties");
		
		properties=new Properties();
		properties.load(fileInputStream);
		
		return properties;
		
	}
    
	//Loads driver
	public static void getDriver(String browser) throws IOException 
	{
		
		loadPropertyFile();
		
		baseUrl=properties.getProperty("url");
		String chromePath=properties.getProperty("chromepath");
		String firefoxPath=properties.getProperty("firefoxpath");


		if(browser.equalsIgnoreCase("firefox"))
		{
			testCase.log(Status.INFO, "Launching FireFox Browser");

			System.setProperty("webdriver.gecko.driver", firefoxPath);
	        FirefoxOptions firefoxOptions = new FirefoxOptions();
	        driver = new FirefoxDriver(firefoxOptions);
			
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			testCase.log(Status.INFO, "Launching Chrome Browser");

			System.setProperty("webdriver.chrome.driver", chromePath);
			driver=new ChromeDriver();

		}	
			
	}
	
	//close the driver
	public static void closeDriver()
	{
		driver.quit();
	}
	
	
	public void getDriverGrid(String browser) throws IOException
	{
		
		loadPropertyFile();
		//String browser=properties.getProperty("browser");
		baseUrl=properties.getProperty("url");
		String hubUrl="http://192.168.43.251:4444/wd/hub";
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setBrowserName(browser);
		capabilities.setPlatform(Platform.WINDOWS);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options= new ChromeOptions();
			options.merge(capabilities);
			
			try {
				driver=new RemoteWebDriver(new URL(hubUrl),options);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions firefoxOptions=new FirefoxOptions();
			firefoxOptions.merge(capabilities);
			
			try {
				driver=new RemoteWebDriver(new URL(hubUrl),firefoxOptions);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}

