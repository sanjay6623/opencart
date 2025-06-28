package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC006_SearchProductEmptyTest extends BaseClass {

	@Test
	public void verifyProductSearchNegitive() {

		logger.info("***********T006_SearchProductTestEmpty_Started********************");
		HomePage hp = new HomePage(driver);
		hp.searchProductEmpty(p.getProperty("productsearchEmpty"));

		SearchPage sp = new SearchPage(driver);
		sp.searchvalidationEmpty();

		if (sp.searchvalidationEmpty().equals("There is no product that matches the search criteria.")) {

			Assert.assertTrue(true);
			logger.info("search product with Empty value passed " + sp.searchvalidationEmpty());

		}

		else {
			logger.info("Search Product Test failed:");
			logger.debug("debug");
			Assert.fail();
		}

	}

}
