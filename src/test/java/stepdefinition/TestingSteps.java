package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.PendingException;

public class TestingSteps {
	
	static WebDriver driver;
	private String bodyText = "Automation QA test for Incubyte";
	private String recipient = "tahabikanerwal@gmail.com"; //Edit mail as per required
	private String cc = "tahabikanerwal@gmail.com";
	private String subject = "Incubyte";
	XSSFWorkbook wb;
	XSSFSheet sheet;
	WebElement sendButton;
	List<WebElement> list;
	
	public void takeScreeenShot(String filepath) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE); 	
		File dest = new File(filepath);
		FileHandler.copy(src, dest);
	}
	
	public void getExcelData(String filepath) throws IOException {
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		 wb = new XSSFWorkbook(fis);
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
	public void user_is_logged_in_to_the_account() throws IOException {
		/* Since a dummy mail is used sometimes after entering email and password
		 * It asks for mobile number for verification
		 * That part is skipped in this code as it requires real mobile number and OTP
		 * In order to run this code kindly generate a new dummy mail and run the code
		 * Replace the email and password values in the excel sheet as the values are taken from there
		 * And you will be good to go
		 * Thank You!!*/
		WebElement signinButton = driver.findElement(By.linkText("Sign in"));
		signinButton.click();
		getExcelData("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\src\\test\\resources\\data.xls");
		sheet = wb.getSheetAt(0);
		String emailId = sheet.getRow(1).getCell(0).getStringCellValue();
		String password = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(emailId);
		System.out.println(password);
		driver.findElement(By.id("identifierId")).sendKeys(emailId);
		driver.findElement(By.cssSelector("#identifierNext > div > button > span")).click();
		driver.findElement(By.xpath("//input[@name=\'password\']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\'passwordNext\']/div/button/span")).click();
	
	}
	
	@Given("user is already logged in to the account")
	public void user_is_already_logged_in_the_my_account() {
	
	
	}
	
	@When("user click on Compose button")
	public void user_click_on_compose_button() {
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
//		driver.findElement(By.xpath("//img[@class='Ha']")).click();
	}
	
	@When("fill in Recipient Id")
	public void fill_in_recipient_id() {
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
	public void add_cc_recipient() throws IOException, InterruptedException {
		// Element to click CC
		driver.findElement(By.xpath("//span[@class='aB gQ pE']")).click();
		// Element for Adding CC recipient
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys(cc);
		Thread.sleep(3000);
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\mailwithcc.jpg");
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
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\mailwithemoji.jpg");
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
		driver.findElement(By.xpath("//div[@class='a-b-c d-u d-u-F ge-tb-jf-enabled']")).click();
		driver.switchTo().parentFrame();
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\mailwithattachment.jpg");
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']")).click();
		Thread.sleep(2000);
	}
	
	@When("click on delete icon on bottom left")
	public void click_on_delete_icon_on_bottom_left() throws IOException {
		driver.findElement(By.xpath("//div[@class='oh J-Z-I J-J5-Ji T-I-ax7']")).click(); 
	}
	
	@When("schedule the mail to be sent later")
	public void schedule_the_mail_to_be_sent_later() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='G-asx']")).click();
		driver.findElement(By.xpath("//div[@class='J-N yr']")).click();
		driver.findElement(By.xpath("//div[@class='Kj-JD-K7']")).click();
		list = driver.findElements(By.xpath("//div[@class='Aj']"));
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().equalsIgnoreCase("Tomorrow morning")) {
				list.get(i).click();
				Thread.sleep(2000);
				break;
				}
			}
	}
	
	@When("fill in wrong Recipient Id")
	public void fill_in_wrong_recipient_id() {
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("taha@");
	}
	
	@Then("user should be able to send the email to the recipient")
	public void user_should_be_able_to_send_the_email_to_the_recipient() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class=\'T-I J-J5-Ji aoO v7 T-I-atl L3\']")).click();
		Thread.sleep(4000);
	}
	
	@Then("user should not be able to send the email to the recipient")
	public void user_should_not_be_able_to_send_the_email_to_the_recipient() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		Thread.sleep(3000);
	}

	@Then("error message should be displayed")
	public void error_message_should_be_displayed() throws InterruptedException, IOException {
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\mailnotsent.jpg");
		String error = driver.findElement(By.xpath("//div[@class=\"Kj-JD-Jz\"]")).getText();
		System.out.println(error);
		Assert.assertEquals("Please specify at least one recipient.", error);
		Thread.sleep(2000);
		//Element for Ok button on Error Message
		driver.findElement(By.xpath("//button[@name=\'ok\']")).click();
		//Close the compose mail block
		driver.findElement(By.xpath("//img[@class='Ha']")).click();
		Thread.sleep(4000);
	}


	@Then("the draft should be deleted and user should not be able to see it in drafts or anywhere else")
	public void the_draft_should_be_deleted_and_user_should_not_be_able_to_see_it_in_drafts_or_anywhere_else() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//div[@class=\"TN bzz aHS-bnq\"]")).click();
		Thread.sleep(2000);
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\nodraftsaved.jpg");
		Thread.sleep(4000);
		
	}
	
	@Then("user should be able to send mail later")
	public void user_should_be_able_to_send_mail_later() throws IOException {
		driver.findElement(By.linkText("Scheduled")).click();
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\schduledlater.jpg");
	}
	
	@Then("user should get an error")
	public void user_should_get_an_error() throws IOException {
		System.out.println(driver.findElement(By.xpath("//div[@class='Kj-JD-Jz']")).getText());
		takeScreeenShot("C:\\Users\\taha\\eclipse-workspace\\cucumbergmail\\screenshots\\invalidrecipient.jpg");
		driver.findElement(By.name("ok")).click();
		driver.findElement(By.xpath("//img[@class='Ha']")).click();
		
	}
	
	@AfterAll
	public static void after_all() {
		driver.quit();
	}
	

}
