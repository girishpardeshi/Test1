package com.vtiger.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Ignore
	@Test
	public void ValidatevalidLogin()
	{
		logger = extent.createTest("ValidatevalidLogin");
		LoginPage lp = new LoginPage(driver, logger);
		lp.ValidLogin(data.get(0).get(1), data.get(0).get(2));
		HomePage hp =new HomePage(driver, logger);
		//assertEquals(hp.VerifyLogout(), true);
		if(hp.VerifyLogout())
		{
			logger.pass("Logout is displayed");
		}
		else
		{
			logger.fail("Logout Fail");
			
			
		}
		
		extent.flush();
				
	}
	
	@Test
	public void InvalidLogin()
	{
		logger = extent.createTest("ValidateInvalidLogin");
		LoginPage lp = new LoginPage(driver, logger);
		assertEquals(lp.InValidLogin(data.get(1).get(1), data.get(1).get(2)), true);
		if(lp.InValidLogin(data.get(1).get(1), data.get(1).get(2)))
		{
			logger.pass("Error msg is displayed");
		}
		else
		{
			logger.fail("Error msg Fail");
			
			
		}
		
		extent.flush();
				
	}
	
	@Test
	public void VerifyLogo()
	{
		logger=extent.createTest("VerifyLogo");
		LoginPage lp =new LoginPage(driver, logger);
		if(lp.VerifyLogo())
		{
			logger.pass("Logo is displayed");
		}
		else
		{
			logger.fail("Logo fail to displayed");
		}
		extent.flush();
	}
}


