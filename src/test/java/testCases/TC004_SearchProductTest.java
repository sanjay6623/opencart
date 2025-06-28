package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {

	@Test
	public void verifyProductSearch() {

		logger.info("***********SearchProductTest_Started********************");
		HomePage hp = new HomePage(driver);
		hp.searchProduct(p.getProperty("productsearch"));

		SearchPage sp = new SearchPage(driver);
		sp.searchvalidation();

		if (sp.searchvalidation().equals(p.getProperty("productsearch"))) {

			Assert.assertTrue(true);
			logger.info("search product done " + sp.searchvalidation());

		}

		else {
			logger.info("Search Product Test failed:");
			logger.debug("debug");
			Assert.fail();
		}

	}

	
}
