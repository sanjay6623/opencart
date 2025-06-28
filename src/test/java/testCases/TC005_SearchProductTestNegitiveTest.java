package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC005_SearchProductTestNegitiveTest extends BaseClass {

	

	@Test
	public void verifyProductSearchNegitive() {

		logger.info("***********T005_SearchProductTestNegitive_Started********************");
		HomePage hp = new HomePage(driver);
		hp.searchProduct(p.getProperty("productsearchNegitive"));

		SearchPage sp = new SearchPage(driver);
		sp.searchvalidationNegitive();

		if (sp.searchvalidationNegitive().equals("There is no product that matches the search criteria.")) {

			Assert.assertTrue(true);
			logger.info("search product with negtive test passed " + sp.searchvalidationNegitive());

		}

		else {
			logger.info("Search Product Test failed:");
			logger.debug("debug");
			Assert.fail();
		}

	}

}
