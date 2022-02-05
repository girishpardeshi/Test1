package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class HomePage {
	
	private WebDriver driver;
	private ExtentTest logger;
	
		
		public HomePage(WebDriver driver, ExtentTest logger)
		{
			this.driver = driver;
			this.logger = logger;
			PageFactory.initElements(driver, this);
			
		}
	
		@FindBy(linkText="Logout")
		WebElement Logout;
	
	public boolean VerifyLogout()
	{
		return Logout.isDisplayed();
		
	}

}
