package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.genericmethods;

public class LoginPage {
	
	private WebDriver driver;
	private ExtentTest logger;
	
	public LoginPage(WebDriver driver, ExtentTest logger)
	{
		//this.driver = driver;
		//this.logger = logger;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="user_name")
	WebElement username;
	
	@FindBy(name="user_password")
	WebElement userpwd;
	
	@FindBy(name="Login")
	WebElement lgn;
	
	@FindBy(xpath="/html/body/table[1]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]")
	WebElement logo;
	
	@FindBy(xpath="//td[contains(text(),' must specify a valid username and password.')]")
	WebElement Errmsg;
	
	public void ValidLogin(String useriddata, String pwddata)
	{
		
		System.out.println("Girish");
		genericmethods.entervalue(driver, username, useriddata, logger);
		//username.sendKeys(userid);
		genericmethods.entervalue(driver, userpwd, pwddata, logger);
		//userpwd.sendKeys(pwd);
		genericmethods.clickelement(driver, lgn, logger);
		//lgn.click();
		
	}
	
	public boolean InValidLogin(String useriddata, String pwddata)
	{
		genericmethods.entervalue(driver, username, useriddata, logger);
		genericmethods.entervalue(driver, username, useriddata, logger);
		genericmethods.clickelement(driver, lgn, logger);
		return Errmsg.isDisplayed();
		
		
	}
	
	public boolean VerifyLogo()
	{
		return logo.isDisplayed();
		
	}
	
	
	
	
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}