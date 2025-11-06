package Locators;

import org.openqa.selenium.By;

public class L6_Cheques {
    protected final By issueCheque = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[8]/ul/li[5]/ul/li[3]/ul/li[3]/a");
    protected final By searchInput = By.xpath("//*[@id=\"value:1:1:1\"]");
    protected final By findBtn = By.xpath("//*[@id=\"enqsel\"]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td[3]/div/table/tbody/tr/td/a");
    protected final By issueChequeBtn = By.xpath("//*[@id=\"r1\"]/td[12]/a/img");
    protected final By chequeStatusInput = By.xpath("//*[@id=\"fieldName:CHEQUE.STATUS\"]");
    protected final By commitBtn = By.xpath("//*[@id=\"goButton\"]/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[1]/a/img");
    protected final By duplicateoverride = By.xpath("/html/body/div[3]/div[2]/form[1]/div[3]/table/tbody/tr[3]/td/table[1]/tbody/tr/td[1]/a");
    protected final By authorizeCheque = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[8]/ul/li[5]/ul/li[3]/ul/li[5]/a");
    protected final By searchBtn = By.xpath("//*[@id=\"enq_toolHeader\"]/tbody/tr/td[2]/div/table/tbody/tr/td[2]/a/img");
    protected final By searchBtnAuthorize = By.xpath("//*[@id=\"value:1:1:1\"]");
    protected final By findBtnAuthorize = By.xpath("//*[starts-with(@id,'enqsel_ENQUIRY')]//td[3]//a");
    protected final By authorizeBtn = By.xpath("//*[starts-with(@id,'r1_ENQUIRY')]/td[10]//img");

}
