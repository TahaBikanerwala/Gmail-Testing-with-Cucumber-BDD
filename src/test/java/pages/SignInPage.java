package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepdefinition.TestingSteps;

public class SignInPage extends TestingSteps{


	public SignInPage(WebDriver driver) throws IOException {
		this.driver = driver;
	}
	
	
	@FindBy(linkText = "Sign in")
	static
	WebElement signinButton;
	
	@FindBy(id = "identifierId")
	static
	WebElement emailId;
	
	@FindBy(css = "#identifierNext > div > button > span")
	static
	WebElement emailNxtBtn;
	
	@FindBy(xpath = "//input[@name=\'password\']")
	static
	WebElement password;
	
	@FindBy(xpath = "//*[@id=\\'passwordNext\\']/div/button/span")
	static
	WebElement nextBtn;
	
	public static void clickOnSignIn() {
		signinButton.click();
	}
	
	public static void enterEmailId(String email) {
		emailId.sendKeys(email);
	}
	
	public static void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}
	
	public static SignInPage clickOnNextBtn() {
		nextBtn.click();
		return PageFactory.initElements(driver, SignInPage.class);
	}
	
	public static SignInPage clickOnEmailNxt() {
		emailNxtBtn.click();
		return PageFactory.initElements(driver, SignInPage.class);
	}

}
