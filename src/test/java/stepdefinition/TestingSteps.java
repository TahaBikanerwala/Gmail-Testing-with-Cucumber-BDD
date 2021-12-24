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
	public void select_an_emoji() throws IOException {
		//Selecting My Fav Emoji
		driver.findElement(By.xpath("//button[@aria-label='face with tears of joy']")).click();
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailwithemoji.jpg");
	}
	
	@When("click on attachments")
	public void click_on_attachments() throws IOException, InterruptedException {
		// Element to click on Google Drive icon
		driver.findElement(By.xpath("//div[@class='aA7 aaA aMZ']")).click();
		Thread.sleep(2000);
		WebElement myframe = driver.findElement(By.xpath("//iframe[@class='KA-JQ']"));			
		driver.switchTo().frame(myframe);
		driver.findElement(By.xpath("//div[@class='ge-Di-hc ge-Df-ke']")).click();
		// Element to click on MyDrive
		driver.findElement(By.xpath("//div[@class='ge-Bi-Zb-Nj']")).click();
		driver.findElement(By.xpath("//div[@class='Od-lh-Gf-Q-Ah-pe']")).click();
		Thread.sleep(2000);
	}
	
	@When("insert attachment")
	public void insert_attachment() throws IOException, InterruptedException {
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailwithattachment.jpg");
		driver.findElement(By.xpath("//div[@class='a-b-c d-u d-u-F ge-tb-jf-enabled']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']")).click();
		Thread.sleep(2000);
	}
	
	@When("click on delete icon on bottom left")
	public void click_on_delete_icon_on_bottom_left() throws IOException {
		driver.findElement(By.xpath("//div[@class='oh J-Z-I J-J5-Ji T-I-ax7']")).click(); 
	}
	
	@Then("user should be able to send the email to the recipient")
	public void user_should_be_able_to_send_the_email_to_the_recipient() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class=\'T-I J-J5-Ji aoO v7 T-I-atl L3\']")).click();
		Thread.sleep(4000);
	}
	
	@Then("user should not be able to send the email to the recipient")
	public void user_should_not_be_able_to_send_the_email_to_the_recipient() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//div[@class=\'T-I J-J5-Ji aoO v7 T-I-atl L3\']")).click();
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailnotsent.jpg");
		String error = driver.findElement(By.xpath("//div[@class=\"Kj-JD-Jz\"]")).getText();
		Thread.sleep(4000);
	}

	@Then("error message should be displayed")
	public void error_message_should_be_displayed() throws InterruptedException, IOException {
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailnotsent.jpg");
		String error = driver.findElement(By.xpath("//div[@class=\"Kj-JD-Jz\"]")).getText();
		System.out.println(error);
		//Element for Ok button on Error Message
		driver.findElement(By.xpath("//button[@name=\'ok\']")).click();
		//Close the compose mail block
		driver.findElement(By.xpath("//img[@class='Ha']")).click();
		Thread.sleep(4000);
	}


	@Then("the draft should be deleted and user should not be able to see it in drafts or anywhere else")
	public void the_draft_should_be_deleted_and_user_should_not_be_able_to_see_it_in_drafts_or_anywhere_else() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//div[@class=\"TN bzz aHS-bnq\"]")).click();
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\mailnotsent.jpg");
		Thread.sleep(4000);
	}
	

}
