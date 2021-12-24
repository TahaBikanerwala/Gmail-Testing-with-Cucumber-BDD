package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.cucumber.java.PendingException;

public class TestingSteps {
	
	static WebDriver driver;
	private String bodyText = "Automation QA test for Incubyte";
	private String recipient = "tahabikanerwal@gmail.com"; //Edit mail as per required
	private String cc = "tahabikanerwal@gmail.com";
	private String subject = "Incubyte";
	WebElement sendButton;
	
	public void takeScreeenShot(String filepath) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		
		File src = screenshot.getScreenshotAs(OutputType.FILE); 
				
		File dest = new File(filepath);
		
		FileHandler.copy(src, dest);
	}
	
	
	@Given("Chrome browser and webdriver is installed")
	
	public void chrome_browser_and_webdriver_is_installed() {
		System.setProperty("webdriver.chrome.driver", "S:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	    
	}
	
	@Then("user should be able to launch Gmail webapp")
	public void user_should_be_able_to_launch_gmail_webapp() {
		driver.get("https://www.google.com/intl/en-GB/gmail/about/#");
	}
	
	@Given("user is logged in to the account")
	public void user_is_logged_in_to_the_account() {
		WebElement signinButton = driver.findElement(By.linkText("Sign in"));
		signinButton.click();
		driver.findElement(By.id("identifierId")).sendKeys("dummymail.586458@gmail.com");
		driver.findElement(By.cssSelector("#identifierNext > div > button > span")).click();
		driver.findElement(By.xpath("//input[@name=\'password\']")).sendKeys("dummymail@6");
		driver.findElement(By.xpath("//*[@id=\'passwordNext\']/div/button/span")).click();
	
	}
	
	@Given("user is already logged in to the account")
	public void user_is_already_logged_in_the_my_account() {
	
	
	}
	
	@When("user click on Compose button")
	public void user_click_on_compose_button() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.xpath("//img[@class='Ha']")).click();
	}
	
	@When("fill in Recipient Id")
	public void fill_in_recipient_id() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(recipient);
	}
	
	@When("fill in Subject")
	public void fill_in_subject() {
		driver.findElement(By.className("aoT")).sendKeys(subject);
		
	}
	
	@When("fill in Body")
	public void fill_in_body() {
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']")).sendKeys(bodyText);
	}
	
	@When("add cc recipient")
	public void add_cc_recipient() throws IOException {
		// Element to click CC
		driver.findElement(By.xpath("//span[@class='aB gQ pE']")).click();
		// Element for Adding CC recipient
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys(cc);
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailwithcc.jpg");
	}
	
	@When("click on Emoji icon")
	public void click_on_Emoji_icon() {
		//Element to click on emoji icon
		driver.findElement(By.xpath("//div[@class='QT aaA aMZ']")).click(); 
		//ELement to switch to emoji tab
		driver.findElement(By.xpath("//button[@title='Show face emoticons']")).click(); 
	}
	
	@When("select an emoji")
	public void select_an_emoji() {
		//Selecting My Fav Emoji
		driver.findElement(By.xpath("//button[@aria-label='face with tears of joy']")).click();
	}
	
	@Then("user should be able to send the email to the recipient")
	public void user_should_be_able_to_send_the_email_to_the_recipient() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class=\'T-I J-J5-Ji aoO v7 T-I-atl L3\']")).click();
		Thread.sleep(4000);
	}



	

}
