package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;


@Epic("EPIC - 100 : Design login page for open cart application...")
@Story("Us -101: login page features")
public class LoginPageTest extends BaseTest {

	@Description("login page title test")
	@Test
	public void loginPageTitleTest() {
		String title =loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page url test")
	@Test
	public void loginPageUrlTest() {
		String url =loginPage.getLoginPageUrl();
		System.out.println("url is :"+url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("this is testing forgot pwd link on login page")
	@Test
	public void forgotpwdLoginTest() {
		Assert.assertTrue(loginPage.isForgotPwsLinkExist());
	}
	
	@Description("positive test case for login with correct credentials.. ")
	@Test
	public void loginTest() {
		actPage=loginPage.doLogin(prop.getProperty("usernaame").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(actPage.isLogoLinkExist());
	}
	
}
