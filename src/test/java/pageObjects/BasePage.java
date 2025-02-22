package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage   {
	
	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
    public void waitForElement(org.openqa.selenium.WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

    }

}
