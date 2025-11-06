package Locators;

import org.openqa.selenium.By;

public class L3_Customer
{
    protected final By individualcustomer = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[2]/ul/li[1]/a");
    protected final By authorizecustomer = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[2]/ul/li[12]/a");
    protected final By MenuFrame = By.xpath("//frame[starts-with(@name, 'menu')]");

    // Create form
    protected final By sector =By.xpath( "//*[@id='fieldName:SECTOR']");
    // Customer Information Fields
    protected final By title = By.xpath("//*[@id='fieldName:TITLE']");
    protected final By givenname = By.xpath("//*[@id='fieldName:GIVEN.NAMES']");
    protected final By fullname = By.xpath("//*[@id='fieldName:NAME.1:1']");
    protected final By shortname = By.xpath("//*[@id='fieldName:SHORT.NAME:1']");
    protected final By accountofficer = By.xpath("//*[@id='fieldName:ACCOUNT.OFFICER']");
    protected final By target = By.xpath("//*[@id='fieldName:TARGET']");
    protected final By industry = By.xpath("//*[@id='fieldName:INDUSTRY']");
    protected final By customerstatus = By.xpath("//*[@id='fieldName:CUSTOMER.STATUS']");
    protected final By nationality = By.xpath("//*[@id='fieldName:NATIONALITY']");
    protected final By residence = By.xpath("//*[@id='fieldName:RESIDENCE']");
    protected final By physicaladdress = By.xpath("//*[@id='headtab']/tbody/tr/td[2]/a");
    protected final By gbstreet = By.xpath("//*[@id='fieldName:STREET:1']");

    // Gender Radio Buttons
    protected final By gendermale = By.xpath("/html/body/div[3]/div[2]/form[1]/div[4]/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/table/tbody/tr/td[2]/input");
    protected final By genderfemale = By.xpath("/html/body/div[3]/div[2]/form[1]/div[4]/table/tbody/tr[3]/td/table[1]/tbody/tr[5]/td[3]/table/tbody/tr/td[1]/input");

    // Mnemonic
    protected final By mnemonic = By.xpath("//*[@id='fieldName:MNEMONIC']");

    // Commit Button
    protected final By commitbtn = By.xpath("//*[@id='goButton']/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[1]/a/img");

    // Duplicate Override

    protected final By duplicateoverride = By.xpath("/html/body/div[3]/div[2]/form[1]/div[3]/table/tbody/tr[3]/td/table[1]/tbody/tr/td[1]/a");
    protected final By duplicateoverridemsg = By.xpath("/html/body/div[3]/div[2]/form[1]/div[3]/table/tbody/tr[4]/td/table[1]/tbody/tr/td[1]/a");
    // Document Message & Checkbox
    protected final By ReceivedDocMsg = By.xpath("/html/body/div[3]/div[2]/form[1]/div[3]/table/tbody/tr[3]/td/table/tbody/tr/td[2]");
    protected final By ReceivedDocCheckbox = By.xpath("/html/body/div[3]/div[2]/form[1]/div[3]/table/tbody/tr[3]/td/table/tbody/tr/td[3]/select");

    // Transaction Complete
    protected final By TxnCompleteMsg = By.xpath("/html/body/div[3]/div[2]/form[1]/div[4]/table/tbody/tr/td/table/tbody/tr[2]/td[2]/table[2]/tbody/tr/td");

    // Authorization Popup
    protected final By AuthorizeMsg = By.xpath("//*[@id='enqheader']/tbody/tr/td");
    protected final By AuthorizeBtn = By.xpath("/html/body/table/tbody/tr[2]/td/div[3]/div[2]/form[1]/div[2]/table/thead/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[5]/a/img");


};
