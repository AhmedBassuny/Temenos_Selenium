import Driver.Chrome_Driver;
import Pages.P1_Login;
import Pages.P2_HomePage;
import Pages.P3_Customer;
import Pages.P4_Accounts;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateSavingAccount extends BaseTest {
    @Test
    public void SavingAccountCreation() throws InterruptedException, IOException {
        WebDriver driver = Chrome_Driver.getDriver();
        P1_Login login_obj = new P1_Login(driver);
        P2_HomePage home_obj = new P2_HomePage(driver);
        P3_Customer customer_obj = new P3_Customer(driver);
//        Thread.sleep(16000);
        login_obj.login("INPUTTBSG", "1234567");
        home_obj.clickOnUserMenu();
        home_obj.clickOnCustomer();
        customer_obj.createindividualcustomer("2");
        home_obj.signOff();
//        Thread.sleep(16000);
        login_obj.login("AUTHORBSG", "1234567");
        home_obj.clickOnUserMenu();
        home_obj.clickOnCustomer();
        customer_obj.authorizecustomer();
        home_obj.signOff();
//        Thread.sleep(16000);
        P4_Accounts accounts_obj = new P4_Accounts(driver, customer_obj.getTransactionNumber(), customer_obj.getCustomerId());
        login_obj.login("INPUTTBSG", "1234567");
        home_obj.clickOnUserMenu();
        home_obj.clickOnAccount();
        accounts_obj.createSavingAccount();
        home_obj.signOff();
//        Thread.sleep(16000);
        login_obj.login("AUTHORBSG", "1234567");
        home_obj.clickOnUserMenu();
        home_obj.clickOnAccount();
        accounts_obj.authorizeAccount();
        accounts_obj.exportAccountToExcel("INPUTTBSG", "AUTHORBSG");
        home_obj.signOff();


    }
}
