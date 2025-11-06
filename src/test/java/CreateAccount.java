import Driver.Chrome_Driver;
import Pages.*;
import Utilities.Reporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;

import static Driver.Chrome_Driver.*;

public class CreateAccount extends BaseTest {
    @Test
    public void CurrentAccountCreation() throws InterruptedException, IOException {
        WebDriver driver = Chrome_Driver.getDriver();
        P1_Login login_obj = new P1_Login(driver);
        P2_HomePage home_obj = new P2_HomePage(driver);
        P3_Customer customer_obj = new P3_Customer(driver);
        Reporter.init(true, true, Path.of("target", "reports", "report.log"));
        login_obj.login("INPUTT", "123456");
        home_obj.clickOnUserMenu();
        home_obj.clickOnCustomer();
        customer_obj.createindividualcustomer("1");
        home_obj.signOff();
//        login_obj.login("AUTHOR", "1234567");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnCustomer();
//        customer_obj.authorizecustomer();
//        home_obj.signOff();
//        P4_Accounts accounts_obj = new P4_Accounts(driver, customer_obj.getTransactionNumber(), customer_obj.getCustomerId());
//        login_obj.login("INPUTT", "123456");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnAccount();
//        accounts_obj.createCurrentAccount();
//        home_obj.signOff();
//        login_obj.login("AUTHOR", "1234567");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnAccount();
//        accounts_obj.authorizeAccount();
//        accounts_obj.exportAccountToExcel("INPUTT", "AUTHOR");
//        home_obj.signOff();
//        P5_Teller teller_obj = new P5_Teller(driver, "121088");
//        login_obj.login("INPUTT", "123456");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnRetialOperations();
//        home_obj.clickOnAccountTransactions();
//        home_obj.clickOnTeller();
//        teller_obj.depositCash("1266");
//        home_obj.signOff();
//        login_obj.login("INPUTT", "123456");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnRetialOperations();
//        home_obj.clickOnAccountTransactions();
//        home_obj.clickOnTeller();
//        teller_obj.withdrawalWithoutCheque("500");
//        home_obj.signOff();
//        login_obj.login("AUTHOR", "1234567");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnRetialOperations();
//        home_obj.clickOnAccountTransactions();
//        home_obj.clickOnTeller();
//        teller_obj.authorizeCashTransaction();
//        home_obj.signOff();
//        login_obj.login("INPUTT", "123456");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnRetialOperations();
//        home_obj.clickOnAccountTransactions();
//        home_obj.clickOnTeller();
//        teller_obj.accountToAccountTransfer("120634","200");
//        home_obj.signOff();
//        login_obj.login("AUTHOR", "1234567");
//        home_obj.clickOnUserMenu();
//        home_obj.clickOnRetialOperations();
//        home_obj.clickOnAccountTransactions();
//        home_obj.clickOnTeller();
//        teller_obj.authorizeATATransfer();
//        home_obj.signOff();



    }

}

