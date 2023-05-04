package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;
import java.util.ResourceBundle;

public class BasePage {
    protected ResourceBundle config;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        config = ResourceBundle.getBundle("config", Locale.getDefault());
        this.driver = driver;
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

    public void skipAds() {
        WebElement adsFrame = driver.findElement(By.id("aswift_7"));
        driver.switchTo().frame(adsFrame);
        driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
        driver.switchTo().defaultContent();
    }

    public void close() {
        driver.close();
    }
}
