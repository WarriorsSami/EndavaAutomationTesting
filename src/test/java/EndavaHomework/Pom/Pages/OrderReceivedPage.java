package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderReceivedPage extends BasePage {
    private final By cashOnDeliveryPaymentMethodFirstLocator = By.xpath("//*[@id=\"page-35\"]/div/div[1]/ul/li[4]/strong");
    private final By cashOnDeliveryPaymentMethodSecondLocator = By.xpath("//*[@id=\"page-35\"]/div/div[1]/table/tfoot/tr[3]/td");

    public OrderReceivedPage(WebDriver driver) {
        super(driver);
    }

    public String getCashOnDeliveryPaymentMethodFirst() {
        return driver.findElement(cashOnDeliveryPaymentMethodFirstLocator).getText();
    }

    public String getCashOnDeliveryPaymentMethodSecond() {
        return driver.findElement(cashOnDeliveryPaymentMethodSecondLocator).getText();
    }
}
