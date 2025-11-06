package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Chrome_Driver {

    // Thread-safe WebDriver instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Setup method to initialize ChromeDriver per thread
    public static void setupChrome() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
        options.addArguments("--incognito");
        options.addArguments("--window-size=1920,1080");

        driver.set(new ChromeDriver(options));
        getDriver().get("http://localhost:9091/R22MBR/servlet/BrowserServlet");
    }


    // Getter for the current thread's WebDriver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit and clean up the WebDriver for the current thread
    public static void chromeQuit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
