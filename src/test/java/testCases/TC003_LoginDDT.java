package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")//getting provider from different class
	
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		try {
			logger.info("-----------------Starting TC003_LoginDDT-------------");
		HomePage hp=new HomePage(driver);
		logger.info("Clicking clickMyAccount ");
		hp.clickMyAccount();
		logger.info("Clicking LoginLink ");
		hp.clickLogin();
		

		LoginPage lp = new LoginPage(driver);
		logger.info("Providing customer details...");
		lp.sendemail(email);
		logger.info("email sent...");
		lp.sendpassword(pwd);
		logger.info("Password send...");
		lp.clicklogin();
		logger.info("Login click...");
		
		logger.info("------------Verifying page-----------");
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetpage=map.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				logger.info("Page verified...");
				map.clickLogout();
				logger.info("logout clicked...");
				
				Assert.assertTrue(true);
				
			}
		
		else
		{
			Assert.assertTrue(false);
		}
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetpage==true)
			{
				
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
			
		}
}
		catch(Exception e)
		{

			Assert.fail();
		}
		logger.info("-----------------Finished TC003_LoginDDT-------------");
	}
	
}
