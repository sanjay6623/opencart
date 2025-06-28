package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) { //constructor
		
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement Register;
	@FindBy(xpath="(//a[normalize-space()='Login'])[1]") WebElement LoginLink;
	@FindBy(xpath="//input[@name='search']") WebElement search;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']") WebElement searchClick;

	
	public void clickMyAccount() {
		linkMyAccount.click();
		
	}
	
	public void clickRegister() {
		
		Register.click();
	}
	
	public void clickLogin() {
		LoginLink.click();
		
	}
	
	public void searchProduct(String searchProduct) {
		
		search.sendKeys(searchProduct);
		searchClick.click();
		
	}
	
	public void searchProductEmpty(String searchProductEmpty) {
		
		
		search.sendKeys(searchProductEmpty);
		searchClick.click();
		
	}
}
