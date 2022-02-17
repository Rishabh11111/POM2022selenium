package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private ElementUtil util;
	private WebDriver driver;
	
	By productHeader = By.cssSelector("div#content h1");
	By productImage = By.cssSelector("ul.thumbnails img");
	By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	By quantity = By.id("input-quantity");
	By addCardBtn = By.id("button-cart");
	
	private Map<String,String> productMap ;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		util = new ElementUtil(driver);
	}
	
	public String getProductHeaderName() {
		return util.doGetText(productHeader);
	}
	public int getProductImageCount() {
		return util.waitForElementsVisible(productImage, 10).size();
	}

	public Map<String, String> getProductInfo() {
		productMap = new TreeMap<String,String>();
		productMap.put("name", getProductHeaderName());
		getProuctMetaData();
		getProuctPriceData();
		return productMap;
	}
	
	private void getProuctMetaData() {
		
		List<WebElement> metaDataList = util.getElements(productMetaData);
		for(WebElement e:metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
						
		}
	}

private void getProuctPriceData() {
		
		List<WebElement> metaPriceList = util.getElements(productMetaData);
		String price = metaPriceList.get(0).getText().trim();
		String extraPrice = metaPriceList.get(1).getText().trim();
		productMap.put("price", extraPrice);
		productMap.put("ExtraPrice", extraPrice);
	}
	
	
	
}
