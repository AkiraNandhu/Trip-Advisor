/*
 * Team Name : Mind Benders
 * This file includes Functions related to Data driven in excel
 * This achieve code re-useability
 */
package com.cognizant.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

public class ExcelUtilities
{
	static XSSFWorkbook Myworkbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static int crusie_row=0;

	
	//Gets Excel data
	public static Object [][] getExcelData(String sheetName) throws FileNotFoundException ,IOException
	{
		//Get the excel file path
		//Using the sheet name passed to this method, read the data and store it in a object array.
		//Store only the column value in the array.
		
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\cognizant\\utilities\\Excel_Data.xlsx";

		Object [][] excelData;
		FileInputStream Xcelfile = new FileInputStream(filePath);
		Myworkbook = new XSSFWorkbook(Xcelfile);
		sheet = Myworkbook.getSheet(sheetName);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int columnCount=sheet.getRow(1).getPhysicalNumberOfCells();
		
		//System.out.println("Rows :"+rowCount+" col :"+columnCount);
		excelData=new Object[rowCount-1][columnCount];
		int count=0;
		for(int i=1;i<rowCount;i++)
		{
			if(count<rowCount)
			{
				row=sheet.getRow(i);
				//System.out.println("Row :"+i+row);
				for(int j=0;j<columnCount;j++)
				{
					cell=row.getCell(j);
					
					//System.out.println("col :"+j+cell);    //XSSFCell.CELL_TYPE_
					
					switch(cell.getCellType())
					{
						case NUMERIC:
							excelData[count][j]=cell.getNumericCellValue();
							break;
						case STRING:
							excelData[count][j]=cell.getStringCellValue();
							break;
							
					}
				
				}
				count++;
			
				
				//System.out.println("data :"+i+ " : "+j+": "+excelData[i][j]);
			}
			 
		}
  	 
       Myworkbook.close();
       Xcelfile.close();
       return excelData;
       
			
	}
	
	
	//Get Status (PAss/Fail) of test case and update in excel
	public static void excelStatusReport(String testCaseName, boolean status) 
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\cognizant\\utilities\\Excel_StatusReport.xlsx";

		String resultStatus;
		if(status)
			resultStatus="Pass";
		else
			resultStatus="Fail";
		try
		{
	
			FileInputStream file=new FileInputStream(new File(filePath));
			
			XSSFWorkbook  Myworkbook=new XSSFWorkbook(file);
			XSSFSheet sheet= Myworkbook.getSheet("StatusReport");
			
			 int totalRow=sheet.getLastRowNum()+1;
			 
			 for(int i=1;i<totalRow;i++)
			 {
				 XSSFRow row= sheet.getRow(i);
				 String cell=row.getCell(1).getStringCellValue();
				 if(cell.equalsIgnoreCase(testCaseName))
				 {
					 row.createCell(2).setCellValue(resultStatus);
					 file.close();
					 FileOutputStream fos=new FileOutputStream(new File(filePath));
					 Myworkbook.write(fos);
					 fos.close();
					 Myworkbook.close();
					 break;
				 }
			 }
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Write the Result into the excel file
	public static  void writeExcelResult(String sheetName,String[] result,int column)
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\cognizant\\utilities\\Excel_ResultReport.xlsx";
		
		try
		{
			FileInputStream file=new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
	        XSSFSheet sheet = workbook.getSheet(sheetName);
	        int totalRow=1;
	        
	        for(int i=0;i<result.length;i++)
	        {
	        	XSSFRow row= sheet.createRow(totalRow);
	        	row.createCell(column).setCellValue(result[i]);
	        	totalRow++;
	        	
	        }
	        file.close();
			FileOutputStream fileOutputStream=new FileOutputStream(new File(filePath));
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			workbook.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

}
