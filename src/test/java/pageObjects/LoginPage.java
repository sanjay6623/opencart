package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(id = "input-email")
	WebElement logEmail;
	@FindBy(id = "input-password")
	WebElement logPass;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement clickLoginBtn;

	public void setValidEmail(String email) {
		logEmail.sendKeys(email);
	}

	public void setValidPass(String pwd) {
		logPass.sendKeys(pwd);
	}
	public void clickLoginBtn() {
		clickLoginBtn.click();
		
	}

}
