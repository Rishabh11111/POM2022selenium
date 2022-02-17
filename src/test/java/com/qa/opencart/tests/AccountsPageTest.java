package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		 actPage = loginPage.doLogin(prop.getProperty("usernaame").trim(),prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest() {
		String acttitle = actPage.getAccountPageTitle();
		System.out.println("title is"+ acttitle);
		Assert.assertEquals(acttitle, Constants.Accounts_Page_Title);
	}
	
	@Test
	public void accPageUrlTest() {
		String url = actPage.getAccountPageUrl();
		System.out.println("url is"+ url);
		Assert.assertTrue(url.contains(Constants.Accounts_Page_URL_FRACTION));
	}
	@Test
	public void accPageHeader() {
		String header = actPage.getAccPageHeader();
		System.out.println("header is "+ header);
		Assert.assertEquals(header, Constants.Accounts_Page_HEADER);
	}
	@Test
	public void logoLinkExistTest() {
		
		Assert.assertTrue(actPage.isLogoLinkExist());
	}
	@Test
	public void searchExistTest() {
		
		Assert.assertTrue(actPage.searchExist());
	}
	@Test
	public void AccPagesectionTest() {
		
		List<String> accountList = actPage.sectionList();
		System.out.println("account list iis:"+ accountList);
		Assert.assertEquals(accountList, Constants.Accounts_Page_Section_List);
	}
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}							
		};
	}
	@Test(dataProvider="productData")
	public void serchIcon(String productName) {
		resultsPage = actPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount()>0);
	}
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}							
		};
	}
	@Test(dataProvider="productSelectData")
	public void selectProduct(String productName,String mainProductName) {
		resultsPage=actPage.doSearch(productName);
	    productPage = resultsPage.selectProduct(mainProductName);
	   Assert.assertEquals(productPage.getProductHeaderName(),mainProductName);
	}
	
}
