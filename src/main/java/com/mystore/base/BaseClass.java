package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;
	//public static WebDriver driver;

	//Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver=new ThreadLocal<>();
	@BeforeSuite(groups= {"smoke","sanity","regression"})
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
					"\\Configuration\\config.properties");
			prop=new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	
	public static void launchApp(String browserName) {
		//String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			//Set browser to ThreadLocalMap

			driver.set(new ChromeDriver());

		}
		else if(browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();

			driver.set(new FirefoxDriver());

		}

		getDriver().manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		Action.pageLoadTimeOut(getDriver(), 30);
		Action.implicitWait(getDriver(), 20);
		getDriver().get(prop.getProperty("url"));

	}

	@AfterSuite
	public void afterSuite() {

		ExtentManager.endReport();
	}

}
