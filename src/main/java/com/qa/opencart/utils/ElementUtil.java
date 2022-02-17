package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil JsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		JsUtil = new JavaScriptUtil(driver);
	}
	
	public By getby(String locatortype,String locatorvalue) {
		By locator=null;
		switch (locatortype) {
		case "id":
			locator=By.id(locatorvalue);
			
			break;
		case "name":
			locator=By.name(locatorvalue);
			
			break;
		case "classname":
			locator=By.className(locatorvalue);
			
			break;
		case "xpath":
			locator=By.xpath(locatorvalue);
			
			break;
			
		case "css":
			locator=By.cssSelector(locatorvalue);
			
			break;
		case "linkText":
			locator=By.linkText(locatorvalue);
			
			break;
		case "partiallinktext":
			locator=By.partialLinkText(locatorvalue);
			
			break;
		case "tagname":
			locator=By.tagName(locatorvalue);
			
			break;

		default:
			break;
		}
		return locator;
		
	}
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public String doGetAttribute(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public  String doGetText(By locator) {
		return getElement(locator).getText();
		
	}
	public void doClick(By locator) {
		getElement( locator).click();		
	}
	
	public void doClick(String locatortype,String locatorvalue) {
		getElement( getby(locatortype, locatorvalue)).click();
	}
	
	public  WebElement getElement(By locator) {
	WebElement ele =  driver.findElement(locator);
	if(Boolean.parseBoolean(DriverFactory.highlight)) {
		JsUtil.flash(ele);
	}
	return ele;
	}
	
	public void doSendkey(By locator,String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		
	}
	public void doSendkey(String locatortype,String locatorvalue, String value) {
		getElement(getby(locatortype,locatorvalue)).sendKeys(value);
		
	}
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  int getElementCount(By locator) {
	    return getElements(locator).size();
		}
	public  void printElementsText(By locator) {
		List<WebElement> element=getElements(locator);
		for(WebElement e:element) {
			   String text=e.getText();
			   System.out.println(text);
		   }	
	}
	public  List<String> getElementsText(By locator) {
		List<WebElement> element=getElements(locator);
		List<String> eleTextList= new ArrayList<String>();
		for(WebElement e:element) {
			   String text=e.getText();
			   if(!text.isEmpty()) {
			   eleTextList.add(text);
			   }
		   }
		return eleTextList;
		
	}
	public void clickOnLink(By locator,String LinkText) {
		List <WebElement> element= getElements(locator);
	    System.out.println(element.size());
	   for(WebElement e:element) {
		  String text= e.getText();
		  System.out.println(text);
		 if(text.contains(LinkText));
		  e.click();
		  break;
		   
	   }
	}
	   /********************DropDown************/
	public  void doSelectDropDownByIndex(By locator,int index) {
		Select select=new Select(getElement( locator));
		select.selectByIndex(index);
	}
	public  void doSelectDropDownByValue(By locator,String value) {
		Select select=new Select(getElement( locator));
		select.selectByValue(value);;
	}
	public  void doSelectDropDownByVisible(By locator,String visible) {
		Select select=new Select(getElement( locator));
		select.selectByVisibleText(visible);;
	}
	   /***********************Actions method**********/
	   
	public  void ActiondoSendkey(By locator,String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	public  void actiondoClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	   
	  /**********waitutil************/
	/**
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does not necessarily mean that the element is visible
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitForElementPresence(By locator , int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		   
	}
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  List<WebElement> waitForElementsPresence(By locator,int timeOut) {
		 WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(timeOut));
		   return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 *  Visibility means that the element is not only displayed but
	 * also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitForElementVisible(By locator , int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		   
	}
	public  WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(timeOut));
	    wait.pollingEvery(Duration.ofSeconds(pollingTime));
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**  an expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	
	
	public  List<WebElement> waitForElementsVisible(By locator,int timeOut) {
		 WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(timeOut));
		   return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public  WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(locator + " is not found within the given time......");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
	}
	
	public  WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(locator + " is not found within the given time......");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
		
		
	}
	//non webElement wait:=url,title
	public  Boolean waitForPageTitle(String title , int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.titleContains(title));
	}
	public  Boolean waitForActPageTitle(String acttitle , int timeOut) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.titleIs(acttitle));
	}
	
	public  String doGetPageTitleContais(String title , int timeOut) {
		if(waitForPageTitle(title, timeOut)){
		return driver.getTitle();	
		}
		return null;
	}
	public  String doGetPageTitleIs(String title , int timeOut) {
		if(waitForActPageTitle(title, timeOut)){
		return driver.getTitle();	
		}
		return null;
	}
	/**
	 * An expectation for the URL of the current page to contain specific text
	 * @param urlFraction
	 * @param timeOut
	 * @return
	 */
	public  String waitForUrlContains(String urlFraction , int timeOut) {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		   if(wait.until(ExpectedConditions.urlContains(urlFraction))){
			   return driver.getCurrentUrl();
		    	
		    }
		   return null;
	}
	/**
	 * An expectation for the URL of the current page to be a specific url.
	 * @param urlFraction
	 * @param timeOut
	 * @return
	 */
	public  String waitForUrlToBe(String urlFraction , int timeOut) {
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		   if(wait.until(ExpectedConditions.urlToBe(urlFraction))){
			   return driver.getCurrentUrl();
		    	
		    }
		   return null;
	}
	/***************waitAlert*********/
	public  Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	    return wait.until(ExpectedConditions.alertIsPresent());
	}
	public  String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	public  void  acceptAlert(int timeOut) {
		 waitForAlert(timeOut).accept();
	}
	public  void dismissAlertText(int timeOut) {
		 waitForAlert(timeOut).dismiss();
	}

	/***************waitForFrame****************/
	public WebDriver waitForFrameByIndex(int timeout, int index) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		   return  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	public  WebDriver waitForFrameByLocator(int timeout, By framLocator) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		   return  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framLocator));
	}
	
	/***************click**********/
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public  void  clickWhenReady(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	
}