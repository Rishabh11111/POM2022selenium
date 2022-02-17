package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		actPage = loginPage.doLogin(prop.getProperty("usernaame").trim(),prop.getProperty("password").trim());
	}
	
	@Test
	public void productHeaderTest() {
		resultsPage = actPage.doSearch("Macbook");
		productPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productPage.getProductHeaderName(),"MacBook Pro");
	}
	@DataProvider
	public Object[][] productsData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",Constants.MACBOOKPRO_IMAGES_COUNT},
			{"Macbook","MacBook Air",Constants.MACBOOKPRO_IMAGES_COUNT},
			{"iMac","iMac",Constants.IMAC_IMAGES_COUNT}								
		};
	}
	
	@Test(dataProvider = "productsData")
	public void productImageCountTest(String prodoctName,String mainProductName,int totalImage ) {
		resultsPage = actPage.doSearch(prodoctName);
		productPage = resultsPage.selectProduct(mainProductName);				
		int imageCount = productPage.getProductImageCount();
		System.out.println("total image of"+ mainProductName+ ":" +imageCount);
		Assert.assertEquals(imageCount, totalImage);
	}
	
	//use HashMap  to print data in unorder
	
//	product is :Macbook pro
//	Brand:Apple
//	Availability:Out Of Stock
//	price:Product Code: Product 18
//	name:MacBook Pro
//	Product Code:Product 18
//	ExtraPrice:Product Code: Product 18
//	Reward Points:800
//	PASSED: productDataTest
	
	////use LinkedHashMap  to print data in order
	
//	product is :MacBook Pro
//	name:MacBook Pro
//	Brand:Apple
//	Product Code:Product 18
//	Reward Points:800
//	Availability:Out Of Stock
//	price:Product Code: Product 18
//	ExtraPrice:Product Code: Product 18
//	PASSED: productDataTest
	
	//use TreeMap  to print data in shorted from
	
	//product is :MacBook Pro
//	Availability:Out Of Stock
//	Brand:Apple
//	ExtraPrice:Product Code: Product 18
//	Product Code:Product 18
//	Reward Points:800
//	name:MacBook Pro
//	price:Product Code: Product 18
//	PASSED: productDataTest
	
	@Test
	public void productDataTest() {
		resultsPage = actPage.doSearch("Macbook");
		productPage = resultsPage.selectProduct("MacBook Pro");
		 Map<String,String> actProductInfoMap = productPage.getProductInfo();
		 actProductInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		 softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		 softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		 softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		 softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		 softAssert.assertEquals(actProductInfoMap.get("Availability"), "Out Of Stock");
		 softAssert.assertAll();
		 
	}

}
