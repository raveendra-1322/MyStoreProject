package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class LoginPageTest extends BaseClass{
	
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"smoke","sanity","regression"})
	public void setUp(String browser) {
		launchApp(browser);
		indexPage=new IndexPage();
		//loginPage=new LoginPage();
	}
	@Test(dataProvider="credentials", dataProviderClass=DataProviders.class,groups= {"smoke","sanity"})
	public void loginTest(String uname,String password) {
		Log.startTestCase("loginTest");
		Log.info("click on signin");
		loginPage=indexPage.clickOnSignin();
		Log.info("enter username and password");
		//homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage=loginPage.login(uname, password,homePage);
		String actualURL=homePage.getCurrURL();
		Log.info("verify the login or not");
		String expectedURL="http://automationpractice.com/index.php?controller=my-account";
		Assert.assertEquals(actualURL, expectedURL);
		Log.endTestCase("loginTest");
	}
	
	@AfterMethod(groups= {"smoke","sanity","regression"})
	public void tearDown() {
		
		getDriver().quit();
	}
	
	
	
	
}
