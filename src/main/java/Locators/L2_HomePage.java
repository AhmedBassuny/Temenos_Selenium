package Locators;
import org.openqa.selenium.By;

public class L2_HomePage
{

    protected final By BannerFrame = By.xpath("//frame[starts-with(@id, 'banner')]");

    protected final By signoff = By.xpath("//*[@id=\"pane_\"]/div[1]/div/table/tbody/tr/td[3]/a");
    protected final By usermenu = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/span");
    protected final By customer = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[2]/span");
    protected final By account = By.xpath("/html/body/div[3]/ul[1]/li/ul/li[4]/span");
    protected final By adminmenu = By.xpath("//*[@id=\"pane_\"]/ul[2]/li/span");
    protected final By rolebasedhomepage = By.xpath("//*[@id=\"pane_\"]/ul[3]/li/span");
    protected final By MenuFrame = By.xpath("//frame[starts-with(@name, 'menu')]");
    protected final By retialOperations = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[8]/span");
    protected final By accountTransactions = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[8]/ul/li[5]/span");
    protected final By teller = By.xpath("//*[@id=\"pane_\"]/ul[1]/li/ul/li[8]/ul/li[5]/ul/li[1]/span");
    protected final By cheques = By.xpath("//*[@id=\"imgError\"]");
}
