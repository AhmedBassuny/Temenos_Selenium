import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import Driver.Chrome_Driver;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        Chrome_Driver.setupChrome();
    }

    @AfterMethod
    public void tearDown() {
        Chrome_Driver.chromeQuit();
    }
}
