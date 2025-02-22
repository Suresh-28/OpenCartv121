package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;


	
	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup (String os, String br) throws IOException
	{
		
		//Loading config file
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p =new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		//os
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome");
			break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");
			break;
			default: System.out.println("No Matching browse"); return;
			
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.206.1:4444/wd/hub"), capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;		
			}
		}
		

		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();

	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
		
	}
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(7);
		return generatedstring;
	}
	
	public String randomenumber() {
		String generatedString1=RandomStringUtils.randomNumeric(10);
		return generatedString1;
	}
	
	public String randomepass() {
		String generatedString2=RandomStringUtils.randomAlphabetic(3);
		String generatedString3=RandomStringUtils.randomNumeric(3);
		return generatedString2+generatedString3;

}
	public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

}
