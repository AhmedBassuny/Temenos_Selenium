package Locators;

import org.openqa.selenium.By;

public class L4_Account {
    protected final By currentaccount = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[4]/ul/li[1]/a");
    protected final By savingaccount = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[4]/ul/li[2]/a");
    protected final By authorizeaccount = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[4]/ul/li[4]/a");
    protected final By customerid = By.xpath("//*[@id=\"fieldName:CUSTOMER\"]");
    protected final By commitbutton = By.xpath("//*[@id=\"goButton\"]/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[1]/a/img");
    protected final By fieldnameaccount = By.xpath("//*[@id=\"enri_CUSTOMER\"]");
    protected final By currency = By.xpath("//*[@id=\"fieldName:CURRENCY\"]");
    protected final By accountnumber = By.xpath("//*[@id=\"messages\"]/tbody/tr[2]/td[2]/table[2]/tbody/tr/td");
    protected final By searchAuthorizeAccount = By.xpath("//*[@id=\"enq_toolHeader\"]/tbody/tr/td[2]/div/table/tbody/tr/td[2]/a");
    protected final By searchInputAuthAccount = By.xpath("//*[@id=\"value:2:1:1\"]");
    protected final By findButtonAuthAccount = By.xpath("//*[starts-with(@id,'enqsel_ENQUIRY')]//td[3]//a");
    protected final By authButton = By.xpath("//*[starts-with(@id,'r1_ENQUIRY')]/td[7]//img");
    protected final By authButton2 = By.xpath("//*[@id=\"goButton\"]/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[5]/a/img");



}
