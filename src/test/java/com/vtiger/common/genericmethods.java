 package com.vtiger.common;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class genericmethods {

	public static void entervalue(WebDriver driver, WebElement ele, String data, ExtentTest logger)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys(data);
			if(ele.getAttribute("value").equals(data))
			{
				System.out.println("PASS");
				logger.pass(data+"enter successfully within textbox");
			}
			else
			{
				System.out.println("FAILED");
				logger.fail(data+"did not enter successfully within textbox  <span class='label end-time'><a<href='"+getscreenshot(driver)+"'>screenshot</a></span>");
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	public static void clickelement(WebDriver driver, WebElement ele, ExtentTest logger)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			logger.pass("Element Clicked Successfully");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Error Captured :"+e.getMessage());
		}
	}
	
	
	public static String getscreenshot(WebDriver driver) throws IOException 
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty(("user.dir")+"/src/test/java/com/vtiger/reports/screenshots/image"+str+".png");
		//Move image file to new destination
		File DestFile=new File(path);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		return path;
	}
	
	
	
	
	
	
}
