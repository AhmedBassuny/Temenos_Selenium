package Pages;

import Locators.L3_Customer;
import Utilities.Data_Utils;
import Utilities.Reporter;
import Utilities.TestDataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class P3_Customer extends L3_Customer {
    private final WebDriver driver;
    private String transactionNumber;
    private Map<String, String> data;
    public String customerId;

    public P3_Customer(WebDriver driver) {
        this.driver = driver;
         this.data = new HashMap<>();
    }

    public String getTransactionNumber() {
        return this.transactionNumber;
    }

    public  String getCustomerId() {
        return this.customerId;
    }

    public void createindividualcustomer(String index) throws InterruptedException, IOException {
        customerId = index;
        this.data = TestDataFactory.generateCustomerData();
        Data_Utils.writeCustomerToCSV(customerId, this.data);
        driver.findElement(super.individualcustomer).click();
        // Store the current (main) window handle
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

// Switch to the new popup window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(super.title)).sendKeys(data.get("title"));

        driver.findElement(super.givenname).sendKeys(this.data.get("givenName"));
        driver.findElement(super.fullname).sendKeys(this.data.get("fullName"));
        driver.findElement(super.shortname).sendKeys(this.data.get("shortName"));
        driver.findElement(super.accountofficer).sendKeys(this.data.get("accountOfficer"));
        driver.findElement(super.target).sendKeys(this.data.get("target"));
        driver.findElement(super.industry).sendKeys(this.data.get("industry"));
        driver.findElement(super.customerstatus).sendKeys(this.data.get("customerStatus"));
        driver.findElement(super.nationality).sendKeys(this.data.get("nationality"));
        driver.findElement(super.residence).sendKeys(this.data.get("residence"));
        if(this.data.get("gender").equals("Female")) {
            driver.findElement(super.genderfemale).click();
        }else {
            driver.findElement(super.gendermale).click();
        }
        driver.findElement(super.mnemonic).sendKeys(this.data.get("mnemonic"));
        driver.findElement(super.sector).sendKeys(this.data.get("sector"));
        driver.findElement(super.physicaladdress).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(super.physicaladdress));
        driver.findElement(super.physicaladdress).click();
        driver.findElement(super.gbstreet).sendKeys(this.data.get("gbStreet"));
        driver.findElement(super.commitbtn).click();
        List<WebElement> elements = driver.findElements(super.duplicateoverride);
        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
            driver.findElement(super.duplicateoverride).click();
        }
        if (driver.findElements(super.ReceivedDocMsg).size() > 0) {
            driver.findElement(super.ReceivedDocCheckbox).sendKeys("RECEIVED");
            List<WebElement> elements2 = driver.findElements(super.duplicateoverridemsg);
            if (!elements2.isEmpty() && elements2.get(0).isDisplayed()) {
                driver.findElement(super.duplicateoverridemsg).click();
            } else {
                driver.findElement(super.commitbtn).click();
            }
        }
        // Wait for the popup message to be visible
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TxnCompleteMsg));
        // Get the text
        String text = msgElement.getText();
        if (!text.isEmpty()) {
            // Extract transaction number using regex
            Pattern pattern = Pattern.compile("Txn Complete:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                this.transactionNumber = matcher.group(1);
                System.out.println("Transaction Number: " + this.transactionNumber);
                Data_Utils.addCustomerInfoToCSV(customerId, "txn_num", this.transactionNumber);
                Reporter.get().info("Customer Created with TXN#: " + this.transactionNumber);
                Reporter.get().reportMap(this.data, "Customer Data");
                Reporter.get().info("Customer report complete");
            }
        }

// Close popup and switch back to main
        driver.close();
        driver.switchTo().window(mainWindow);


    }

    public void authorizecustomer() {
//        WebElement frame = driver.findElement(MenuFrame);
//        driver.switchTo().frame(frame);
        driver.findElement(super.authorizecustomer).click();
        String mainWindow = driver.getWindowHandle();
        // Wait until number of windows increases
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

// Switch to the new popup window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Wait for authorize message to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(AuthorizeMsg));

        // Build the dynamic XPath for the transaction row
        String txnXpath = String.format("//table[1]//tr[td[1][normalize-space()='%s']]/td[6]/a/img", this.transactionNumber);

        List<WebElement> txnRows = driver.findElements(By.xpath(txnXpath));

        if (!txnRows.isEmpty()) {
            // Click on the transaction authorize image
            txnRows.get(0).click();

            // Wait for the authorize button
            WebElement authorizeBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AuthorizeBtn)
            );
            // Click on authorize button
            authorizeBtn.click();
            // Close popup (assuming it's a window)
            driver.close();
            // If popup is a separate window, switch back:
            driver.switchTo().window(mainWindow);
        } else {
            Reporter.get().error("TXN is Not Found");
        }
    }



}
