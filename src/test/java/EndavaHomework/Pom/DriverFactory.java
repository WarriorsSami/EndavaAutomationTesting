package EndavaHomework.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome": {
                var options = new ChromeOptions();
                options.addArguments("load-extension=" + "src/test/resources/adblock/5.6.0_0");
//                options.addArguments("--headless");
                return new ChromeDriver(options);
            }
            case "firefox": {
                var options = new FirefoxOptions();
                options.addArguments("load-extension=" + "src/test/resources/adblock/5.6.0_0");
                options.addArguments("--headless");
                return new FirefoxDriver(options);
            }
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }
}
