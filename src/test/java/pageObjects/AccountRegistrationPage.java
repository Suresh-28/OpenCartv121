package pageObjects;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccountRegistrationPage extends BasePage{


	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor 

		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100));
	}
	
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement inpfirstname;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement inplastname;

@FindBy(xpath="//input[@id='input-email']")
WebElement inpemail;

@FindBy(xpath="//input[@id='input-password']")
WebElement inppassword;

@FindBy(xpath="//input[@id='input-newsletter']")
WebElement subtoggle;


@FindBy(xpath="//input[@name='agree']")
WebElement Pritoggle;


@FindBy(xpath="//button[normalize-space()='Continue']")
WebElement btncontinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement txtconfirm;

public void setfirstname(String fname)
{
	inpfirstname.sendKeys(fname);
	
}
public void setlastname(String lname)
{
	inplastname.sendKeys(lname);
	
}
public void setemail(String txtmail) {
	inpemail.sendKeys(txtmail);
	
}
public void setpassword(String txtpass)
{
	inppassword.sendKeys(txtpass);
}
public void clicksubscribe() {
	subtoggle.click();
	
}


public void clickprivacy()
{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(Pritoggle));

    Actions actions = new Actions(driver);
    actions.moveToElement(Pritoggle).click().perform();
}
public void clickcontinue()
{
	btncontinue.click();
}



public String getConfirmationMsg()
{
	try {
		return(txtconfirm.getText());
				
	}
	catch(Exception e){
		return (e.getMessage());
	}
}
}
