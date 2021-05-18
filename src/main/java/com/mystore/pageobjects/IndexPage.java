package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass{

	public IndexPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//Find the web elements on web pages
	
	@FindBy(xpath="//a[@class='login']")
	WebElement signInBtn;
	
	@FindBy(xpath="//img[@alt='My Store']")
	WebElement logo;
	
	@FindBy(xpath="//input[@id='search_query_top']")
	WebElement searchProductBox;
	
	@FindBy(xpath="//button[@name='submit_search']")
	WebElement searchButton;
	
	//User action methods
	
	public LoginPage clickOnSignin() {
		Action.click(getDriver(), signInBtn);
		return new LoginPage();
		
	}
	
	public boolean validateLogo() {
		
		return Action.isDisplayed(getDriver(), logo);
	} 
	
	public String getMyStroreTitle() {
		
		return getDriver().getTitle();
	}
	
	public SearchResultPage searchProduct(String productName) {
		Action.type(searchProductBox, productName);
		Action.click(getDriver(), searchButton);
		return new SearchResultPage();
		
	}
	
	
}
