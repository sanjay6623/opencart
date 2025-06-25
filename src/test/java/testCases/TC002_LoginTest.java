package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("******Starting TC002 LoingTest**********");

		try {
			HomePage hp = new HomePage(driver);
			logger.info("Home page ");
			hp.clickMyAccount();
			hp.clickLogin();
			LoginPage lp = new LoginPage(driver);
			logger.info("Login page ");
			lp.setValidEmail(p.getProperty("email"));
			lp.setValidPass(p.getProperty("password"));
			lp.clickLoginBtn();
			MyAccountPage map = new MyAccountPage(driver);
			logger.info("My Account Page ");
			map.isMyaccountPage();
			Assert.assertEquals(map.isMyaccountPage(), true);

		} catch (Exception e) {

			Assert.fail();
		}

		logger.info("**********TC002_LogingTest Finish******");
		/*
		 * else {
		 * 
		 * logger.info("My account page not displayed"); Assert.fail(); }
		 */
		/*
		 * if(map.isMyaccountPage()==true) {
		 * 
		 * logger.info("Valid user login done"); Assert.assertTrue(true);
		 * 
		 * } else {
		 * 
		 * logger.info("Valid user login Failed: "); Assert.fail(); }
		 */

	}

}
