package Pages;

import Locators.L4_Account;
import Utilities.Data_Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Utilities;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P4_Accounts extends L4_Account {
    private final WebDriver driver;
    private String transactionNumber;
    private String accountNumber;
    private Map<String, String> data;
    private String customerId;

    public P4_Accounts(WebDriver driver, String transactionNumber, String customerId) {
        this.driver = driver;
        this.transactionNumber = transactionNumber;
        this.customerId = customerId;
    }

    public  String getAccountNumber() {
        return this.accountNumber;
    }

    public void createCurrentAccount() throws InterruptedException {
        driver.findElement(super.currentaccount).click();
        // Store the current (main) window handle
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.findElement(super.customerid).sendKeys(this.transactionNumber);
        driver.findElement(super.commitbutton).click();
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(super.accountnumber));
        // Get the text
        String text = msgElement.getText();
        if (!text.isEmpty()) {
            // Extract transaction number using regex
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.accountNumber = matcher.group(1);
                System.out.println("Account Number: " + this.accountNumber);
                Data_Utils.addCustomerInfo("customer1", "acc_num", this.accountNumber);
            }
            // save account number

            // reload full customer data

            this.data = Data_Utils.readCustomer(this.customerId);
            this.data.put("txn_num", this.transactionNumber);
            this.data.put("acc_num", this.accountNumber);
            Data_Utils.addCustomerInfo(this.customerId, "acc_num", this.accountNumber);
        }

// Close popup and switch back to main
        driver.close();
        driver.switchTo().window(mainWindow);


    }

    public void createSavingAccount() throws InterruptedException {
        driver.findElement(super.savingaccount).click();
        // Store the current (main) window handle
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.findElement(super.customerid).sendKeys(this.transactionNumber);
        driver.findElement(super.currency).sendKeys("USD");
        driver.findElement(super.commitbutton).click();
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(super.accountnumber));
        // Get the text
        String text = msgElement.getText();
        if (!text.isEmpty()) {
            // Extract transaction number using regex
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.accountNumber = matcher.group(1);
                System.out.println("Account Number: " + this.accountNumber);
            }

            this.data = Data_Utils.readCustomer(this.customerId);
            this.data.put("txn_num", this.transactionNumber);
            this.data.put("acc_num", this.accountNumber);
            Data_Utils.addCustomerInfo(this.customerId, "acc_num", this.accountNumber);
        }

// Close popup and switch back to main
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    public void authorizeAccount() throws InterruptedException {
        driver.findElement(super.authorizeaccount).click();
        // Store the current (main) window handle
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchAuthorizeAccount)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.searchInputAuthAccount)).sendKeys(this.accountNumber);
        driver.findElement(super.findButtonAuthAccount).click();
        wait.until(ExpectedConditions.elementToBeClickable(super.authButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.authButton2)).click();

        driver.close();
        // If popup is a separate window, switch back:
        driver.switchTo().window(mainWindow);

    }

    // Export account + customer data to Excel
    public void exportAccountToExcel(String creatorUser, String authorUser) {
        if (this.data != null && !this.data.isEmpty()) {
            System.out.println("Exporting to Excel: " + this.data);
//            Utilities.ExcelExporter.exportMap(this.data, "user_data", creatorUser, authorUser);
        } else {
            System.out.println(" No account data to export. Did you call createCurrentAccount()?");
        }
    }
}
