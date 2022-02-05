package com.vtiger.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	public static List<List<String>> data;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	
	
	
	@BeforeSuite
	public void Launchapp() throws Exception
	{
		Readproperties();
		data=readExcelData();
		creatreport();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("GlobalTimeout"))));	
		
		
	}
	
	
	
	
	@AfterSuite
	public void Closeapp()
	{
		driver.quit();
	}

	public void Readproperties() throws Exception
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/vtiger/common/config.properties");
		prop.load(fis);
		
	
	}
	
	public List<List<String>> readExcelData()
	{
		Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+prop.getProperty("TestDataPath"));
		int rows = xr.getRowCount("Sheet1");
		int columns = xr.getColumnCount("Sheet1");
		List<List<String>> rowlist = new ArrayList<List<String>>();
		for(int i=2;i<=rows;i++)
		{
			List<String> collist = new ArrayList<String>();
			for(int j=0;j<columns;j++)
			{
				String data = xr.getCellData("Sheet1", j, i).trim();
				collist.add(data);			
			}
			rowlist.add(collist);
		}
			return rowlist;	
		
	}
	
	public void creatreport()
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/ExtentReports"+str+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "AutomationTest Hub");
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Girish");
		htmlReporter.config().setDocumentTitle("Title of the report comes here");
		htmlReporter.config().setReportName("Name of the report comes here");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
	}
	
	
	
}
