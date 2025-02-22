package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	


	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement txtconfirm;
	
	public boolean isMyAccountPageExists()
	{
		try {
			return(txtconfirm.isDisplayed());
					
		}
		catch(Exception e){
			return false;
		}
	}

	
	public void clickLogout()
	{

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(lnkLogout));

	    Actions actions = new Actions(driver);
	    actions.moveToElement(lnkLogout).click().perform();
		
	}


}
