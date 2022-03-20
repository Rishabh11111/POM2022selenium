package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private ElementUtil util;
	private WebDriver driver;

	
	private By serachHeader = By.cssSelector("div#content h1");
	 private By productResult = By.cssSelector("div.caption a");
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		util= new ElementUtil(driver);
	}
	
	

	public int getProductListCount() {
		int productCount  = util.waitForElementsVisible(productResult, 10).size();
		System.out.println("product count :" +productCount);
		return productCount;
	}
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("product is :" +mainProductName);
		List<WebElement> searchList = util.waitForElementsVisible(productResult, 10);
		
		for(WebElement e:searchList) {
			String text = e.getText();
			if(text.equalsIgnoreCase(mainProductName)) {
			e.click();
			break;
		}
		
	}
	return new ProductInfoPage(driver);
}
}
