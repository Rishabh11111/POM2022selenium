 package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPages {

	private WebDriver driver;
	private ElementUtil elementutil;
	
	//1.private By locator
	By emailId = By.id("input-email");
	By password = By.id("input-password");
	By loginbtn = By.xpath("//input[@value='Login']");
	By forgottenpass = By.linkText("Forgotten Password");	
	By registerLink = By.linkText("Register");
	
	//2.Page constructor
	public LoginPages(WebDriver driver) {
		this .driver=driver;
		elementutil=new ElementUtil(driver);
	}
	
	//3.page action/method
	@Step("getting the login page title...")
	public String getLoginPageTitle() {
		return elementutil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting the login page url...")
	public String getLoginPageUrl() {
		return elementutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT );
	}
	
	@Step("check forgot pwd link is displayed...")
	public boolean isForgotPwsLinkExist() {
		return elementutil.doIsDisplayed(forgottenpass);
	}
	
	@Step("login with user name : {0} and password: {1}...")
	public AccountsPage doLogin( String username,String pwd) {
		elementutil.doSendkey(emailId, username);
		elementutil.doSendkey(password, pwd);
		elementutil.doClick(loginbtn);
		return new AccountsPage(driver);
		
	}
	@Step("navigating to register page...")
	public RegisterPage goToRegisterPage() {
		elementutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
}
