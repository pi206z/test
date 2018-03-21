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
	System.setProperty("webdriver.gecko.driver", "geckodriver");
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
@Test
public void ElementsVisibilityAndFrontValidation() {
	String ResultHTML = "http://localhost:8080/OSAGOCalculator/Result.html"; //Адрес страницы результата
	driver.get("http://localhost:8080/OSAGOCalculator/");//Переход на страницу калькулятора
	int RandomNegativeValue = (int)Math.random()*(-100); //Генератор случайного отрицательного числа
	int RandomPositiveValueMoreThanPossible = (int)Math.random()*700; //Генератор случайного положительного числа
	driver.findElement(By.xpath("//h1")).isDisplayed(); //Отображение элемента на странице
	driver.findElement(By.xpath("//h2")).isDisplayed();
	driver.findElement(By.xpath("//label")).click(); //Нажатие на элемент
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
	driver.findElement(By.xpath("//h2[2]")).isDisplayed();
	driver.findElement(By.xpath("//h2[3]")).isDisplayed();
	driver.findElement(By.xpath("//h2[4]")).isDisplayed();
	driver.findElement(By.name("Power")).click();
	driver.findElement(By.name("Age")).click();
	driver.findElement(By.name("Experience")).click();
	driver.findElement(By.id("submit")).isDisplayed();
	driver.navigate().refresh(); // обновление страницы
	driver.findElement(By.xpath("//label")).click();
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
	driver.findElement(By.id("submit")).click();
	String CalcHTML = driver.getCurrentUrl(); //Получение текущей ссылки
	assert CalcHTML != ResultHTML: "Ошибка. Осуществлен переход на страницу результата"; //обработка исключения при переходе на страницу результатов
	driver.navigate().refresh();
	driver.findElement(By.name("Power")).sendKeys(String.valueOf(RandomNegativeValue));
    driver.findElement(By.name("Age")).sendKeys(String.valueOf(RandomNegativeValue));
    driver.findElement(By.name("Experience")).sendKeys(String.valueOf(RandomNegativeValue));
    driver.findElement(By.id("submit")).click();
	assert CalcHTML != ResultHTML: "Ошибка. Осуществлен переход на страницу результата";
	driver.navigate().refresh();
	driver.findElement(By.name("Power")).sendKeys(String.valueOf(RandomPositiveValueMoreThanPossible));
    driver.findElement(By.name("Age")).sendKeys(String.valueOf(RandomPositiveValueMoreThanPossible));
    driver.findElement(By.name("Experience")).sendKeys(String.valueOf(RandomPositiveValueMoreThanPossible));
    driver.findElement(By.id("submit")).click();
	assert CalcHTML != ResultHTML: "Ошибка. Осуществлен переход на страницу результата";
	driver.navigate().refresh();
}
@Test
public void ValidationBackIncorrectInput() {
	driver.get("http://localhost:8080/OSAGOCalculator/"); //Переход на страницу калькулятора
	driver.findElement(By.xpath("//label")).click(); //Нажатие на элемент
	driver.findElement(By.xpath("//label[2]")).click();
	driver.findElement(By.xpath("//label[3]")).click();
    driver.findElement(By.name("Power")).sendKeys("70"); //ввод значения в поле Power
    driver.findElement(By.name("Age")).sendKeys("50");
    driver.findElement(By.name("Experience")).sendKeys("20");
    driver.findElement(By.id("submit")).click();
    WebElement ValidationMessage = driver.findElement(By.xpath("//h1"));//Поиск элемента по XPath 
    System.out.println("Пользователю выдано сообщение об ошибке: " + ValidationMessage.getText()); //вывод в консоль сообщение об ошибке (содержмиое тега h1) 
    driver.navigate().back(); //Переход на предыдущую страницу
    driver.navigate().refresh(); //обновление страницы
    driver.findElement(By.xpath("//label")).click(); 
    driver.findElement(By.name("Power")).sendKeys("50");
    driver.findElement(By.name("Age")).sendKeys("20");
    driver.findElement(By.name("Experience")).sendKeys("25");
    driver.findElement(By.id("submit")).click();
    WebElement ValidationMessage2 = driver.findElement(By.xpath("//h1"));
    System.out.println("Пользователю выдано сообщение об ошибке: " + ValidationMessage2.getText());
    driver.navigate().back();
    driver.navigate().refresh();
    driver.findElement(By.xpath("//label[2]")).click();
    driver.findElement(By.xpath("//label")).click();
    driver.findElement(By.name("Power")).sendKeys("60");
    driver.findElement(By.name("Age")).sendKeys("40");
    driver.findElement(By.name("Experience")).sendKeys("20");
    driver.findElement(By.id("submit")).click();
    WebElement ValidationMessage3 = driver.findElement(By.xpath("//h1"));
    System.out.println("Пользователю выдано сообщение об ошибке: " + ValidationMessage3.getText());
    driver.navigate().back();
    driver.navigate().refresh();
    driver.findElement(By.xpath("//label[2]")).click();
    driver.findElement(By.name("Power")).sendKeys("60");
    driver.findElement(By.name("Age")).sendKeys("18");
    driver.findElement(By.name("Experience")).sendKeys("1");
    driver.findElement(By.id("submit")).click();
    WebElement ValidationMessage4= driver.findElement(By.xpath("//h1"));
    System.out.println("Пользователю выдано сообщение об ошибке: " + ValidationMessage4.getText()); 
}
@Test 
public void CalculationTest() {
	driver.get("http://localhost:8080/OSAGOCalculator/"); //Переход на страницу калькулятора
	int PowerUp = 550; int PowerDown = 150; //задание диапазон значений для рандомайзера (Мощность ТС)
	int AgeUp = 99; int AgeDown = 23; // -==- (Возраст водителя)
	int ExperienceUp = 8; int ExperienceDown = 0; // (Стаж водителя) 
	for(int counter = 1; counter <= 10; counter = counter+1) { // Цикл. 10 расчетов со случайно введенными значениями
	String RandomPowerValue = String.valueOf(PowerDown + (int)(Math.random()*PowerUp)) ;
	String RandomAgeValue = String.valueOf(AgeDown + (int)(Math.random()*(AgeUp-AgeDown))) ;
	String RandomExperienceValue = String.valueOf(ExperienceDown + (int)(Math.random()*ExperienceUp)) ;
	driver.findElement(By.xpath("//label[3]")).click(); //Нажатие на элемент
	driver.findElement(By.name("Power")).sendKeys(RandomPowerValue); //Ввод рандомного значения в поле Power
	driver.findElement(By.name("Age")).sendKeys(RandomAgeValue);
	driver.findElement(By.name("Experience")).sendKeys(RandomExperienceValue);
	driver.findElement(By.id("submit")).click();
	driver.findElement(By.xpath("//h1")).click();
	WebElement ResultMessage = driver.findElement(By.xpath("//h1"));
	System.out.println("Расчет №" + counter + "  " + ResultMessage.getText() + ". Использованные данные: Мощность ТС - " + RandomPowerValue + " Возраст водителя - " + RandomAgeValue + " Стаж вождения - " + RandomExperienceValue); //вывод результатов расчета
    driver.navigate().back(); //переход на предыдущую страницу в браузере 
    driver.navigate().refresh(); //Обновление страницы
	}
}
@AfterClass
public static void ClosingTestRun() {
driver.quit();
}
}