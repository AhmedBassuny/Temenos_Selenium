package Pages;

import Locators.L5_Teller;
import Utilities.Data_Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P5_Teller extends L5_Teller {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private String accountNumber;
    private String tellerNumber;

    public P5_Teller(WebDriver driver, String accountNumber) {
        this.driver = driver;
        this.accountNumber = accountNumber;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public void depositCash(String amount) throws InterruptedException {
        driver.findElement(super.tellerOperations).click();
        driver.findElement(super.tellerCash).click();
        driver.findElement(super.cashDepositLocal).click();
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.date)).sendKeys("20220419");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.depositAccountNumber)).sendKeys(this.accountNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.cashAmount)).sendKeys(amount);
        boolean success = false;
        int attempts = 0;
        while (!success && attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(super.depositUnit1)).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(super.depositUnit1)).sendKeys("0");
                success = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
                Thread.sleep(1000); // Wait for 1 second before retrying
            }
        }
        double amt = Double.parseDouble(amount);
        double[] units = {100, 50, 20, 10, 5, 2, 1, 0.5, 0.25, 0.1, 0.05, 0.01};
        By[] locators = {
                super.depositUnit1, super.depositUnit2, super.depositUnit3, super.depositUnit4,
                super.depositUnit5, super.depositUnit6, super.depositUnit7, super.depositUnit8,
                super.depositUnit9, super.depositUnit10, super.depositUnit11, super.depositUnit12
        };

        for (int i = 0; i < units.length; i++) {
            int count = (int) (amt / units[i]);
            amt = Math.round((amt - count * units[i]) * 100.0) / 100.0; // avoid floating point issues
            WebElement unitField = wait.until(ExpectedConditions.visibilityOfElementLocated(locators[i]));
            unitField.clear();
            if (count > 0) {
                unitField.sendKeys(String.valueOf(count));
            }
        }
        driver.findElement(super.commitBtn).click();
        if (driver.findElements(super.acceptOverride).size() > 0) {
            driver.findElement(super.acceptOverride).click();
        }
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(super.successMsg)).getText();

        if (!text.isEmpty()) {
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\w+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.tellerNumber = matcher.group(1);
                System.out.println("Teller Number: " + this.tellerNumber);
            } else {
                System.out.println("No match found.");
            }
        }

        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);
    }

    public void withdrawalWithoutCheque(String amount) throws InterruptedException {
        driver.findElement(super.tellerOperations).click();
        driver.findElement(super.tellerCash).click();
        driver.findElement(super.withdrawalWithoutCheque).click();
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.date)).sendKeys("20220419");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.depositAccountNumber)).sendKeys(this.accountNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.cashAmount)).sendKeys(amount);
        boolean success = false;
        int attempts = 0;
        while (!success && attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(super.withdrawalUnit1)).clear();
                wait.until(ExpectedConditions.visibilityOfElementLocated(super.withdrawalUnit1)).sendKeys("0");
                success = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
                Thread.sleep(1000); // Wait for 1 second before retrying
            }
        }
        double amt = Double.parseDouble(amount);
        double[] units = {100, 50, 20, 10, 5, 2, 1, 0.5, 0.25, 0.1, 0.05, 0.01};
        By[] locators = {
                super.withdrawalUnit1, super.withdrawalUnit2, super.withdrawalUnit3, super.withdrawalUnit4,
                super.withdrawalUnit5, super.withdrawalUnit6, super.withdrawalUnit7, super.withdrawalUnit8,
                super.withdrawalUnit9, super.withdrawalUnit10, super.withdrawalUnit11, super.withdrawalUnit12
        };

        for (int i = 0; i < units.length; i++) {
            int count = (int) (amt / units[i]);
            amt = Math.round((amt - count * units[i]) * 100.0) / 100.0; // avoid floating point issues
            WebElement unitField = wait.until(ExpectedConditions.visibilityOfElementLocated(locators[i]));
            unitField.clear();
            if (count > 0) {
                unitField.sendKeys(String.valueOf(count));
            }
        }
        driver.findElement(super.commitBtn).click();
        if (driver.findElements(super.acceptOverride).size() > 0) {
            driver.findElement(super.acceptOverride).click();
        }
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(super.successMsg)).getText();

        if (!text.isEmpty()) {
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\w+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.tellerNumber = matcher.group(1);
                System.out.println("Teller Number: " + this.tellerNumber);
            } else {
                System.out.println("No match found.");
            }
        }

        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);
    }

    public void authorizeCashTransaction() throws InterruptedException {
        driver.findElement(super.tellerOperations).click();
        driver.findElement(super.tellerCash).click();
        driver.findElement(super.authorizeCashTransactions).click();

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
        WebElement frame=wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeCashFrame));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchInput)).sendKeys(this.tellerNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.findBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(super.authorizeBtn)).click();
        driver.switchTo().window(driver.getWindowHandle());
        WebElement frame2=wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeCashFrame2));
        driver.switchTo().frame(frame2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeBtn2)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.successMsg)).isDisplayed();


        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);
    }

    public void accountToAccountTransfer(String toAccount ,String amount) throws InterruptedException {
        driver.findElement(super.tellerOperations).click();
        driver.findElement(super.accountTransfer).click();
        driver.findElement(super.accountToAccount).click();
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Switch to the new popup window
        // Switch to the new popup window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.debitDate)).sendKeys("20220419");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.creditDate)).sendKeys("20220419");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.debitCurrency)).sendKeys("USD");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.creditCurrency)).sendKeys("USD");
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.cashAmount)).sendKeys(amount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.debitAccount)).sendKeys(toAccount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.creditAccount)).sendKeys(this.accountNumber);
        driver.findElement(super.commitBtn).click();
        if (driver.findElements(super.acceptOverride).size() > 0) {
            driver.findElement(super.acceptOverride).click();
        }
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(super.successMsg)).getText();

        if (!text.isEmpty()) {
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\w+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.tellerNumber = matcher.group(1);
                System.out.println("Teller Number: " + this.tellerNumber);
            } else {
                System.out.println("No match found.");
            }
        }

        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);
    }

    public void authorizeATATransfer() throws InterruptedException {
        driver.findElement(super.tellerOperations).click();
        driver.findElement(super.accountTransfer).click();
        driver.findElement(super.authorizeAccountToAccount).click();

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
        WebElement frame=wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeCashFrame));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchInput)).sendKeys(this.tellerNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.findBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(super.authorizeBtn)).click();
        driver.switchTo().window(driver.getWindowHandle());
        WebElement frame2=wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeCashFrame2));
        driver.switchTo().frame(frame2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.authorizeBtn2)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.successMsg)).isDisplayed();


        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);
    }

}
