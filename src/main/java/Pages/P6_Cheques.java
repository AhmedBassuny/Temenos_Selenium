package Pages;

import Locators.L6_Cheques;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P6_Cheques extends L6_Cheques {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private String accountNumber;

    public  P6_Cheques(WebDriver driver, String accountNumber) {
        this.driver = driver;
        this.accountNumber = accountNumber;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }
    public void issueCheque() throws InterruptedException {

        driver.findElement(issueCheque).click();
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Switch to the new popup window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchInput)).sendKeys(accountNumber);
        driver.findElement(findBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.issueChequeBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.chequeStatusInput)).sendKeys("61");
        driver.findElement(commitBtn).click();
        try {
            driver.findElement(duplicateoverride).click();
        } catch (Exception e) {
            System.out.println("No duplicate override popup");
        }
        driver.close();
        driver.switchTo().window(mainWindow);    }


    public void authorizeCheque() throws InterruptedException {
        driver.findElement(authorizeCheque).click();
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Switch to the new popup window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchBtnAuthorize)).sendKeys(accountNumber);
        driver.findElement(findBtnAuthorize).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeBtn)).click();
        try {
            driver.findElement(duplicateoverride).click();
        } catch (Exception e) {
            System.out.println("No duplicate override popup");
        }
        driver.close();
        driver.switchTo().window(mainWindow);
    }
}
