package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement inpemail;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement inppassword;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement btnlogin;
	
	public void sendemail(String txtmail) {
		inpemail.sendKeys(txtmail);
		
	}
	public void sendpassword(String txtpass)
	{
		inppassword.sendKeys(txtpass);
	}
	
	public void clicklogin()
	{
		btnlogin.click();
	}

	

}
