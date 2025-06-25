package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(id = "input-firstname")
	WebElement regFirstName;
	@FindBy(id = "input-lastname")
	WebElement regLastName;
	@FindBy(id = "input-email")
	WebElement regEmail;
	@FindBy(id = "input-telephone")
	WebElement regMobileNumber;
	@FindBy(id = "input-password")
	WebElement regPassword;
	@FindBy(id = "input-confirm")
	WebElement regConfirmPass;
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement regSubscription;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement regPrivicyPolicy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement regContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement accountCreated;

	public void setFirstName(String fName) {

		regFirstName.sendKeys(fName);
	}

	public void setLastName(String lName) {

		regLastName.sendKeys(lName);
	}

	public void setEmail(String email) {

		regEmail.sendKeys(email);
	}

	public void setMobile(String mobile) {

		regMobileNumber.sendKeys(mobile);
	}

	public void setPassword(String pass) {

		regPassword.sendKeys(pass);
	}

	public void setConfirmPass(String cPass) {

		regConfirmPass.sendKeys(cPass);
	}

	public void clickSub() {

		regSubscription.click();
	}

	public void clickPrivicyPolicy() {

		regPrivicyPolicy.click();
	}

	public void clickContnou() {

		regContinue.click();
	}

	public String regDoneMessage() {

		try {

			return (accountCreated.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
 
	}

}
