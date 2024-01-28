import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;


public class EbankLogin {
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win32 (3)\\chromedriver-win32\\chromedriver.exe");
        driver =new ChromeDriver();

        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }

    @AfterMethod
    public void setDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void testLoginWithEmptyInputs(){
        WebElement btn= driver.findElement(By.xpath("//button"));
        btn.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message-text']")));
        WebElement errorMessageE=driver.findElement(By.xpath("//p[@class='error-message-text']"));
        Assert.assertEquals("Invalid user ID",errorMessageE.getText(),"Error text with empty input fields does not match");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyUserId(){
        WebElement pinE=driver.findElement(By.xpath("//input[@id='pinInput']"));
        pinE.sendKeys("231225");
        WebElement btn= driver.findElement(By.xpath("//button"));
        btn.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message-text']")));
        WebElement errorMessageE=driver.findElement(By.xpath("//p[@class='error-message-text']"));
        Assert.assertEquals("Invalid user ID",errorMessageE.getText(),"Error text with empty User ID do not match");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyPin(){

        WebElement userE=driver.findElement(By.xpath("//input[@id='userIdInput']"));
        userE.sendKeys("142420");
        WebElement btn= driver.findElement(By.xpath("//button"));
        btn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message-text']")));
        WebElement errorMessageE=driver.findElement(By.xpath("//p[@class='error-message-text']"));
        Assert.assertEquals("Invalid PIN",errorMessageE.getText(),"Error text with empty PIN do not match");

    }

    @Test(priority = 4)
    public void testLoginWithInvalidCreds(){
        WebElement userE=driver.findElement(By.xpath("//input[@id='userIdInput']"));
        userE.sendKeys("142420");
        WebElement pinE=driver.findElement(By.xpath("//input[@id='pinInput']"));
        pinE.sendKeys("2312255555");
        WebElement btn= driver.findElement(By.xpath("//button"));
        btn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message-text']")));
        WebElement errorMessageE=driver.findElement(By.xpath("//p[@class='error-message-text']"));
        Assert.assertEquals("User ID and PIN didn't match",errorMessageE.getText(),"Error text with invalid PIN do not match");
    }

    @Test(priority = 5)
    public void testLoginWithValidCreds(){

        WebElement userE=driver.findElement(By.xpath("//input[@id='userIdInput']"));
        userE.sendKeys("142420");
        WebElement pinE=driver.findElement(By.xpath("//input[@id='pinInput']"));
        pinE.sendKeys("231225");
        WebElement btn= driver.findElement(By.xpath("//button"));
        btn.click();

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));

        Assert.assertEquals("https://qaebank.ccbp.tech/",driver.getCurrentUrl(),"URLs do not match");



    }



}
