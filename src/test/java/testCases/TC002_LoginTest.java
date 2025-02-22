package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"}) 
	public void verify_login()
	{
		try {
		logger.info("-----------------Checking TC002_verify_login------------n");
		HomePage hp=new HomePage(driver);
		logger.info("Clicking clickMyAccount ");
		hp.clickMyAccount();
		logger.info("Clicking LoginLink ");
		hp.clickLogin();
		

		LoginPage lp = new LoginPage(driver);
		logger.info("Providing customer details...");
		lp.sendemail(p.getProperty("email"));
		lp.sendpassword(p.getProperty("password"));
		lp.clicklogin();
		
		logger.info("------------Verifying page-----------");
		MyAccountPage map=new MyAccountPage(driver);
		boolean cnfmsg1=map.isMyAccountPageExists();
	
		Assert.assertTrue(cnfmsg1);
	
		}catch(Exception e) {
			
			Assert.fail();
		}
		
	}

}
