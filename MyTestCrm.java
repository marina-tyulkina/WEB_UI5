package homework5;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class MyTestCrm {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static WebDriverWait webDriverWait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

    }

    private void login() {
       driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
       driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
       driver.findElement(By.id("_submit")).click();
    }

    @BeforeTest
        void setUp() {
         WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    void setUpBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        driver.get(LOGIN_PAGE_URL);
        login();

    }

    @Test(description = "Создание нового проекта", enabled = true)
        void createNewMyProject() throws InterruptedException {

        Actions actions = new Actions(driver);
        WebElement projectMenuItem = driver.findElement(By.xpath("//span[text()='Проекты']/ancestor::a"));
        actions.moveToElement(projectMenuItem)
                    .build()
                    .perform();
            driver.findElement(By.xpath("//li[@data-route='crm_project_my']/a")).click();
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Создать проект']")));
            driver.findElement(By.xpath("//a[text()='Создать проект']")).click();
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_project[name]")));
            driver.findElement(By.name("crm_project[name]")).sendKeys("FirstProject");
            driver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys("Test_from_GB");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            driver.findElement(By.name("crm_project[businessUnit]")).click();
            Select businessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
            Thread.sleep(5000);
            businessUnit.selectByVisibleText("Research & Development");
            Select curator = new Select(driver.findElement(By.name("crm_project[curator]")));
            curator.selectByVisibleText("Амелин Владимир");
            Select rp = new Select(driver.findElement(By.name("crm_project[rp]")));
            rp.selectByVisibleText("Воденеев Денис");
            Select administrator = new Select(driver.findElement(By.name("crm_project[administrator]")));
            administrator.selectByVisibleText("Исаева Анастасия");
            Select manager = new Select(driver.findElement(By.name("crm_project[manager]")));
            manager.selectByVisibleText("Ломакина Ксения");
            Select selectContact = new Select(driver.findElement(By.xpath("//span[text()='Выберите контактное лицо']")));
            selectContact.selectByVisibleText("Ivanov Ivan");
            driver.findElement(By.xpath("//*[@class=\"btn btn-success action-button\"]")).click();
            driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();
            driver.findElement(By.xpath("//*[text()='Проект сохранен']"));

            List<WebElement> project = driver.findElements(By.xpath("//*[text()='Проект сохранен']"));
            project.get(0).getText();
            Assert.assertTrue(project.get(0).isDisplayed());
        }



    @Test(description = "Создание нового контрагента", enabled = true)
        void createNewContractPerson() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement counterpartyItem = driver.findElement(By.xpath("//span[text()='Контрагенты']/ancestor::a"));
        actions.moveToElement(counterpartyItem)
                .build()
                .perform();
        driver.findElement(By.xpath("//span[text()='Контактные лица']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[text()='Создать контактное лицо']")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Иванов");
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Петр");
        driver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys("Test_from_GB");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Директор");
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[text()='Контактное лицо сохранено']"));

        List<WebElement> counterAgents = driver.findElements(By.xpath("//*[text()='Контактное лицо сохранено']"));
        counterAgents.get(0).getText();
        Assert.assertTrue(counterAgents.get(0).isDisplayed());
        }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
    }
}
