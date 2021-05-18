/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;

public class IndexPageTest extends BaseClass {

	IndexPage indexPage;

	@Parameters("browser")
	@BeforeMethod(groups= {"smoke","sanity","regression"})
	public void setUp(String browser) {
		launchApp(browser);
		indexPage=new IndexPage();
	}
	
	@Test(groups="smoke")
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		boolean result=indexPage.validateLogo();
		Assert.assertTrue(result);	
		Log.endTestCase("verifyLogo");
	}
	
	@Test(groups="smoke")
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		String actualTitle=indexPage.getMyStroreTitle();
		Assert.assertEquals(actualTitle, "My Store");	
		Log.endTestCase("verifyTitle");
	}
	
	@AfterMethod(groups= {"smoke","sanity","regression"})
	public void tearDown() {
		getDriver().quit();
	}
}
