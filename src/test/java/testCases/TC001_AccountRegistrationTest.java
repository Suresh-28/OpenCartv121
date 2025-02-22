package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		try {
		logger.info("Clicking clickMyAccount ");
		HomePage hp=new HomePage(driver);
		logger.info("Clicking clickMyAccount ");
		hp.clickMyAccount();
		logger.info("Clicking clickRegister ");
		hp.clickRegister();
		
		AccountRegistrationPage repage= new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");
	    repage.setfirstname(randomeString().toUpperCase());
	    repage.setlastname(randomeString().toUpperCase());
	    repage.setemail(randomeString()+ "@gmail.com");
	    
	    String password = randomepass();
	    repage.setpassword(password);
	    repage.clicksubscribe();
	    
	    repage.clickprivacy();
	    repage.clickcontinue();
	    
		logger.info("Validating expected message");
	    String cnfmsg=repage.getConfirmationMsg();
	    if(cnfmsg.equals("Your Account Has Been Created!")) {
	    	Assert.assertTrue(true);
	    }
	    else {
	    	Assert.assertTrue(false);
	    	logger.error("Test failed");
			logger.debug("Debug logs..");
	    }
	   
	}catch(Exception e) {
		
		Assert.fail();
	}
		
	}
	

	
	
}


