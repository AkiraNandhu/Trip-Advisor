/*
 * Team Name : Mind Benders
 * This file includes Functions related to capturing Screenshot
 * This achieve code re-useability
 */
package com.cognizant.utilities;

import java.io.File; 
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public  class ScreenShots extends DriverSetup
{
	//Capture screenshot and stores in specified Folder
	public static void captureScreenShot(String fileName) 
	{
		
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
