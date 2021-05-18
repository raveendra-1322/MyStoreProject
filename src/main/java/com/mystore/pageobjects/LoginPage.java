package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {

	public LoginPage() {

		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath="//input[@id='email']")
	WebElement userName;

	@FindBy(xpath="//input[@id='passwd']")
	WebElement password;

	@FindBy(xpath="//span[normalize-space()='Sign in']")
	WebElement signInBtn;

	@FindBy(xpath="//input[@id='email_create']")
	WebElement emailForNewAcoount;

	@FindBy(xpath="//span[normalize-space()='Create an account']")
	WebElement createNewAccountBtn;


	public HomePage login(String uname,String pwd, HomePage homePage) {

		Action.type(userName, uname);
		Action.type(password, pwd);
		Action.click(getDriver(), signInBtn);
		return new HomePage();

	}
	public AddressPage login1(String uname,String pwd) {

		Action.type(userName, uname);
		Action.type(password, pwd);
		Action.click(getDriver(), signInBtn);
		return new AddressPage();

	}
	public AccountCreationPage createNewAccount(String newEmail) {
		Action.type(emailForNewAcoount, newEmail);
		Action.click(getDriver(), createNewAccountBtn);
		return new AccountCreationPage();

	}




}
