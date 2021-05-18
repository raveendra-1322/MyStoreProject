package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

public class SearchResultPageTest extends BaseClass{
	IndexPage indexPage;
	SearchResultPage searchResultPage;

	@Parameters("browser")
	@BeforeMethod(groups= {"smoke","sanity","regression"})
	public void setUp(String browser) {
		launchApp(browser);
		indexPage=new IndexPage();
	}
	@AfterMethod(groups= {"smoke","sanity","regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test(groups="smoke", dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabulityTest(String productName) {
		Log.startTestCase("productAvailabulityTest");
		searchResultPage=indexPage.searchProduct(productName);

		boolean result=searchResultPage.isProductAvailable();
		Assert.assertTrue(result);
		Log.endTestCase("productAvailabulityTest");
	}

}
