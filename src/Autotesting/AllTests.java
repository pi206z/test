package Autotesting;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AllTests {
	
	private static FirefoxDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser(){
	System.setProperty("webdriver.gecko.driver", "D:\\Workspace\\OSAGOCalculatorTesting\\selenium\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
@Test
public void ElementsVisibility() {
	driver.get("http://localhost:8081/OSAGOCalculator/");
	driver.findElement(By.xpath("//h1")).click();
	driver.findElement(By.xpath("//h2")).click();
	driver.findElement(By.xpath("//label")).click();
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
	driver.findElement(By.xpath("//h2[2]")).click();
	driver.findElement(By.xpath("//h2[3]")).click();
	driver.findElement(By.xpath("//h2[4]")).click();
	driver.findElement(By.name("Power")).click();
	driver.findElement(By.name("Age")).click();
	driver.findElement(By.name("Experience")).click();
	driver.findElement(By.id("submit")).click();
	driver.navigate().refresh();
}
@Test
public void ValidationFrontRegionUsage() {
	String ResultHTML = "http://localhost:8081/OSAGOCalculator/Result.html";
	driver.get("http://localhost:8081/OSAGOCalculator/");
	driver.findElement(By.xpath("//label")).click();
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
	driver.findElement(By.id("submit")).click();
	String CalcHTML = driver.getCurrentUrl();
	assert CalcHTML != ResultHTML: "Redirect to ResultHTML is succeed, error";
	driver.navigate().refresh();	
}
@Test
public void ValidationFrontPower() {
	String ResultHTML = "http://localhost:8081/OSAGOCalculator/Result.html";
	driver.get("http://localhost:8081/OSAGOCalculator/");
	    driver.findElement(By.name("Power")).sendKeys("900");
	    driver.findElement(By.name("Age")).sendKeys("50");
	    driver.findElement(By.name("Experience")).sendKeys("18");
	    driver.findElement(By.id("submit")).click();
		String CalcHTML = driver.getCurrentUrl();
		assert CalcHTML != ResultHTML: "Redirect to ResultHTML is succeed, error";
		driver.navigate().refresh();
}
@Test
public void ValidationFrontAge() {
	String ResultHTML = "http://localhost:8081/OSAGOCalculator/Result.html";
	driver.get("http://localhost:8081/OSAGOCalculator/");
	driver.findElement(By.name("Power")).sendKeys("70");
	driver.findElement(By.name("Age")).sendKeys("150");
	driver.findElement(By.name("Experience")).sendKeys("12");
	driver.findElement(By.id("submit")).click();
	String CalcHTML = driver.getCurrentUrl();
	assert CalcHTML != ResultHTML: "Redirect to ResultHTML is succeed, error";
	driver.navigate().refresh();
}
@Test
public void ValidationFrontExperience() {
	String ResultHTML = "http://localhost:8081/OSAGOCalculator/Result.html";
	driver.get("http://localhost:8081/OSAGOCalculator/");
    driver.findElement(By.name("Power")).sendKeys("70");
    driver.findElement(By.name("Age")).sendKeys("80");
    driver.findElement(By.name("Experience")).sendKeys("-2");
    driver.findElement(By.id("submit")).click();
    String CalcHTML = driver.getCurrentUrl();
	assert CalcHTML != ResultHTML: "Redirect to ResultHTML is succeed, error";
	driver.navigate().refresh();
}
@Test
public void ValidationBackMultipleChooseRegion() {
	driver.get("http://localhost:8081/OSAGOCalculator/");
	driver.findElement(By.xpath("//label")).click();
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
    driver.findElement(By.name("Power")).sendKeys("70");
    driver.findElement(By.name("Age")).sendKeys("50");
    driver.findElement(By.name("Experience")).sendKeys("20");
    driver.findElement(By.id("submit")).click();
    WebElement ValidationMessage = driver.findElement(By.xpath("//h1"));
    System.out.println("Пользователю выдано сообщение: " + ValidationMessage.getText());
    driver.navigate().back();
    driver.navigate().refresh();
}
@Test
public void CalculationForMoscow() {
	driver.get("http://localhost:8081/OSAGOCalculator/");
	driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.name("Power")).sendKeys("70");
    driver.findElement(By.name("Age")).sendKeys("30");
    driver.findElement(By.name("Experience")).sendKeys("5");
    driver.findElement(By.id("submit")).click();
    WebElement ResultMessage = driver.findElement(By.xpath("//h1"));
    System.out.println("Для Москвы " + ResultMessage.getText());
    driver.navigate().back();
    driver.navigate().refresh();
}
@Test
public void CalculationForSPB() {
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.name("Power")).sendKeys("114");
	driver.findElement(By.name("Age")).sendKeys("20");
	driver.findElement(By.name("Experience")).sendKeys("2");
	driver.findElement(By.id("submit")).click();
	driver.findElement(By.xpath("//h1")).click();
	WebElement ResultMessage = driver.findElement(By.xpath("//h1"));
	System.out.println("Для СПб " +ResultMessage.getText());
	driver.navigate().back();
	driver.navigate().refresh();
}
@Test 
public void CalculationForUfa() {
	driver.findElement(By.xpath("//label[3]")).click();
	driver.findElement(By.name("Power")).sendKeys("200");
	driver.findElement(By.name("Age")).sendKeys("18");
	driver.findElement(By.name("Experience")).sendKeys("0");
	driver.findElement(By.id("submit")).click();
	driver.findElement(By.xpath("//h1")).click();
	WebElement ResultMessage = driver.findElement(By.xpath("//h1"));
	System.out.println("Для Уфы " + ResultMessage.getText());
    driver.navigate().back();
    driver.navigate().refresh();
}
@AfterClass
public static void ClosingTestRun() {
driver.quit();
}
}
		