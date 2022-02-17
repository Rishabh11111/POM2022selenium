package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties property;
	public OptionsManager optionManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * this method used to initilize driver by using bowsername
	 * @param browsername
	 * @return this method return webdriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browsername=prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		System.out.println("browser name :" + browsername);
		optionManager= new OptionsManager(prop);
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	driver=new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		}
		
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		}
		
		else if(browsername.equalsIgnoreCase("safari")) {
			
		//	driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("pass right browser");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
		
	}
	
	/**
	 * this method return thread local copy of webdriver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties init_pro() {
		property =new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resorces/config/config.properties");
			property.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return property;
	}
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
