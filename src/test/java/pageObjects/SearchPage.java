package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//a[normalize-space()='iMac']")
	WebElement searchvalidation;
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement searchMessage;
	
	

	public String searchvalidation() {

		String searchTxt = searchvalidation.getText();
		return searchTxt;
	}

	public String searchvalidationNegitive() {

		return searchMessage.getText();
	}
	public String searchvalidationEmpty() {

		return searchMessage.getText();
	}

}
