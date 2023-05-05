package EndavaHomework.Pom.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Locale;
import java.util.ResourceBundle;

public class BasePage {
    protected ResourceBundle config;
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        config = ResourceBundle.getBundle("config", Locale.getDefault());
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(10))
                .pollingEvery(java.time.Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public String getConfigProperty(String key) {
        return config.getString(key);
    }

    public void navigateToUrl(String url) {
        driver.get(getConfigProperty(url));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void close() {
        driver.quit();
    }
}
