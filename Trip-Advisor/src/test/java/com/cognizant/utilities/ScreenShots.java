package com.cognizant.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public  class ScreenShots extends DriverSetup
{
	public static void captureScreenShot(String fileName) 
	{
		System.out.println("In screenshot");
		String FilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+fileName+".png";
		TakesScreenshot screenShot =((TakesScreenshot)driver);
		File sourceFile=screenShot.getScreenshotAs(OutputType.FILE);
		
		File destinationFile=new File(FilePath);
		try  {
			FileHandler.copy(sourceFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
