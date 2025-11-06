package Pages;

import org.openqa.selenium.WebDriver;

import Locators.L1_Login;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class P1_Login extends L1_Login
{
    private final WebDriver driver;
    public P1_Login(WebDriver driver) {this.driver = driver;}
    public void login (String username, String password)
    {
        driver.findElement(super.username).sendKeys(username);
        driver.findElement(super.password).sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(super.signin));
        driver.findElement(super.signin).click();
    }
}