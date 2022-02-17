package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	
	private ElementUtil util;
	private WebDriver driver;
	
	
	private By header = By.cssSelector("div#logo a");
	private By sectionList= By.cssSelector("div#content h2");
	private By LogoLink = By.linkText("Logout");
	private By search = By.name("search");
	private By serchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		util=new ElementUtil(driver);
	}
	

	public String getAccountPageTitle() {
		return util.doGetPageTitleIs(Constants.Accounts_Page_Title, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountPageUrl() {
		return util.waitForUrlContains(Constants.Accounts_Page_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
	}
	public String getAccPageHeader() {
		return util.doGetText(header);
		
	}
	public boolean isLogoLinkExist() {
		return util.doIsDisplayed(LogoLink);
	}
	public boolean logOut() {
		if(isLogoLinkExist()) {
		util.doClick(LogoLink);
		return true;
		}
		return false;
	}
	public List<String> sectionList() {
		List<WebElement> list = util.waitForElementsVisible(sectionList, 10);
		List<String> stList = new ArrayList<String>();
		for(WebElement e:list) {
			String val = e.getText();
			stList.add(val);
		}
		return stList;
	}
	public boolean searchExist() {
		return util.doIsDisplayed(search);
	}
	public ResultsPage doSearch(String productName) {
		if(searchExist()) {
			util.doSendkey(search, productName);
			util.doClick(serchButton);			
		}
		
		return new ResultsPage(driver);
	}
	
}
