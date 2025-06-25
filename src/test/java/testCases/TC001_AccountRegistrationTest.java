package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	
	public void verify_account_registration() {

		logger.info("****** Starting TC001_AccountRegistationTest********");
		try {
		HomePage hp = new HomePage(driver); // HomePage object creation
		hp.clickMyAccount();
		logger.info("*Clicked on MyAccount link*");
		hp.clickRegister();
		logger.info("*Clicked on Register link*");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver); // AccountRegisration Page Object
		logger.info("*Providing customer details*");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com"); // randomly generated email
		regpage.setMobile(randomNumber());
		String password = randomAlphaNumaric();
		regpage.setPassword(password);
		regpage.setConfirmPass(password);
		regpage.clickSub();
		regpage.clickPrivicyPolicy();
		regpage.clickContnou();
		String actualMessage = regpage.regDoneMessage();
		String expectedmessage = "Your Account Has Been Created!";
		logger.info("*Validating expected Message*");
		if(actualMessage.equals(expectedmessage)) {
			
			
			logger.info("Messge Aafter Account Registration:"+actualMessage);
			Assert.assertTrue(true);
		}
		else {
			 
		
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(actualMessage, expectedmessage);
		}
		catch(Exception e){
			
			
			Assert.fail();
		}

	}

}
