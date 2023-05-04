package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BasePage {
    private final By subtotalPriceLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/table/tbody/tr[1]/td/span/text()");
    private final By totalPriceLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/table/tbody/tr[3]/td/strong/span/text()");
    private final By proceedToCheckoutButtonLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/div/a");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public int getSubtotalPrice() {
        var subtotalString = driver.findElement(subtotalPriceLocator).getText();
        return Integer.parseInt(subtotalString.substring(1, subtotalString.length() - 1));
    }

    public int getTotalPrice() {
        var totalString = driver.findElement(totalPriceLocator).getText();
        return Integer.parseInt(totalString.substring(1, totalString.length() - 1));
    }

    public CheckoutPage clickProceedToCheckoutButton() {
        driver.findElement(proceedToCheckoutButtonLocator).click();
        return new CheckoutPage(driver);
    }
}
