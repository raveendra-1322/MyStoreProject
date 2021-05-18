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

public class HomePageTest extends BaseClass{

	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"smoke","sanity","regression"})
	public void setUp(String browser) {
		launchApp(browser);
		indexPage=new IndexPage();
	}
	
	@Test(groups="smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void wishListTest(String uname, String pswd) {
		Log.startTestCase("wishListTest");
		loginPage=indexPage.clickOnSignin();
		homePage=loginPage.login(uname,pswd,homePage);
		boolean result=homePage.validateMyWishList();
		Assert.assertTrue(result);
		Log.endTestCase("wishListTest");
	}
	
	@Test(groups="smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void orderHistoryAndDetailsTest(String uname, String pswd) {
		Log.startTestCase("orderHistoryAndDetailsTest");
		loginPage=indexPage.clickOnSignin();
		homePage=loginPage.login(uname,pswd,homePage);
		boolean result=homePage.validateOrderHistory();
		Assert.assertTrue(result);
		Log.endTestCase("orderHistoryAndDetailsTest");
	}
	@AfterMethod(groups= {"smoke","sanity","regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
}
