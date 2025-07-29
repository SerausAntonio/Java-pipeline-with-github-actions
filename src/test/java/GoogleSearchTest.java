import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchTest {
    public WebDriver driver;
    public WebDriverWait wait;
    @Test
    public void searchTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.google.com/?hl=en&gl=US&acceptLang=nl");
        Assert.assertEquals(driver.getTitle(),"Google");
        driver.findElement(By.xpath("//*[text()='Accept all']")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("q"))));
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("selenium");
        searchBox.sendKeys(Keys.RETURN);
        driver.quit();
    }
}
