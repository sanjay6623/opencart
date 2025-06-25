package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LogingDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven") //getting data provider from different class
	public void loginDDT(String email, String pwd, String exp) {
		logger.info("***********TC003_LoginDDT_Started*******");

		try {
		// Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		// Login page
		LoginPage lp = new LoginPage(driver);
		lp.setValidEmail(email);
		lp.setValidPass(pwd);
		lp.clickLoginBtn();
		// MyAccount
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyaccountPage();
		
		
		/*Data is valid- login success - test pass - logout
										login failed - test fail

		Data is invalid - login success - test fail -logout
							login failed -- test pass
		*/
		
		
		
		if(exp.equalsIgnoreCase("Valid")) {
			
			if(targetPage==true) {
				map.clickLogout();
				Assert.assertTrue(true);
				
				
			}
			else {
				
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			
			if(targetPage==true) {
				
				map.clickLogout();
				Assert.assertTrue(false);
			}
			
			else {
				Assert.assertTrue(true);
			}
			
			
		}
		}catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***********TC003_LoginDDT_Finish***********");

	}

}
