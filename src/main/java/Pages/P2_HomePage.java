package Pages;

import Locators.L2_HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;


public class P2_HomePage extends L2_HomePage {
    private final WebDriver driver;
    private WebDriverWait wait;
    public P2_HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    public void  signOff() {
        WebElement frame = driver.findElement(BannerFrame);
        driver.switchTo().frame(frame);
        driver.findElement(super.signoff).click();
        driver.switchTo().defaultContent();
    }
    public  void  clickOnUserMenu() {
        WebDriver frame = this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(MenuFrame));
//        driver.switchTo().frame((WebElement) frame);
// Click user menu
        this.wait.until(ExpectedConditions.elementToBeClickable(super.usermenu)).click();
    }
    public void clickOnCustomer(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.customer)).click();
    }
    public void clickOnAccount(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.account)).click();

    }
    public void clickOnRetialOperations(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.retialOperations)).click();
    }
    public void clickOnAccountTransactions(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.accountTransactions)).click();
    }
    public void clickOnTeller(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.teller)).click();
    }

    public void clickOnCheques(){
        this.wait.until(ExpectedConditions.elementToBeClickable(super.cheques)).click();
    }

}
