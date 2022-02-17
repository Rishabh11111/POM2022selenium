package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subcribeYes = By.xpath("(//label[@class=\"radio-inline\"])[position()=1]/input");
	private By subcribeNo = By.xpath("(//label[@class=\"radio-inline\"])[position()=2]/input");
	
	private By agreeCheckBox =By.xpath("//input[@type=\"checkbox\" and  @name=\"agree\"]");
	private By Continewbtn=By.xpath("//input[@type=\"submit\" and @value=\"Continue\"]");
	
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	private By successUser = By.cssSelector("div#content h1");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		util=new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName ,String emailId,
			String telephone, String password, String subscribe) {
		util.doSendkey(this.firstName, firstName);
		util.doSendkey(this.lastName, lastName);
		util.doSendkey(this.emailId, emailId);
		util.doSendkey(this.telephone, telephone);
		util.doSendkey(this.password, password);
		util.doSendkey(this.confirmPassword, password);
		
		if(subscribe.equals(subcribeYes)) {
			util.doClick(subcribeYes);
		}
		else {
			util.doClick(subcribeNo);
		}
		
		util.doClick(agreeCheckBox);
		util.doClick(Continewbtn);
		
		String sussess = util.doGetText(successUser);
		if(sussess.contains(Constants.REGISTER_SUCCESS_MESG)){
			util.doClick(logout);
			util.doClick(registerLink);
		    return true;	
		}
		
		return false;
		
	}
	
}
	
