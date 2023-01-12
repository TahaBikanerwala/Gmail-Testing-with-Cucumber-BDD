package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import stepdefinition.TestingSteps;

public class SignInPage extends TestingSteps {

	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(linkText = "Sign in")
	WebElement signinButton;
	
	@FindBy(id = "identifierId")
	WebElement emailId;
	
	@FindBy(css = "#identifierNext > div > button > span")
	WebElement emailNxtBtn;
	
	@FindBy(xpath = "//input[@name=\'password\']")
	WebElement password;
	
	@FindBy(xpath = "//*[@id=\\'passwordNext\\']/div/button/span")
	WebElement nextBtn;
	
	public void clickOnSignIn() {
		signinButton.click();
	}
	
	public void enterEmailId(String email) {
		emailId.sendKeys(email);
	}
	
	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}
	
	public void clickOnNextBtn() {
		nextBtn.click();
	}
	
	public void clickOnEmailNxt() {
		emailNxtBtn.click();
	}
	
}
