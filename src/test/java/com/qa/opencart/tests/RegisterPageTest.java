package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetUp(){
		regPage = loginPage.goToRegisterPage();
	}
	public String GetRandomNum() {
		Random random = new Random();
		String email = "testautomatoinNovBatch"+random.nextInt(1000)+"@gmail.com";
		return email;
		
	}
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][]=ExcelUtil.getTestData(Constants.Register_Sheet_Name);
		return regData;
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName ,
			String telephone, String password, String subscribe){
		Assert.assertTrue(regPage.accountRegistration( firstName,  lastName , GetRandomNum(),
				 telephone,  password, subscribe));
	}

}
