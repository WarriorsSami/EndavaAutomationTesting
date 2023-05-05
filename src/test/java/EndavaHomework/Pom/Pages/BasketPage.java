package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BasePage {
    private final By subtotalPriceLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/table/tbody/tr[1]/td/span");
    private final By totalPriceLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/table/tbody/tr[3]/td/strong/span");
    private final By proceedToCheckoutButtonLocator = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/div");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public double getSubtotalPrice() {
        var subtotalString = driver.findElement(subtotalPriceLocator).getText();
        return Double.parseDouble(subtotalString.substring(1, subtotalString.length() - 1));
    }

    public double getTotalPrice() {
        var totalString = driver.findElement(totalPriceLocator).getText();
        return Double.parseDouble(totalString.substring(1, totalString.length() - 1));
    }

    public CheckoutPage clickProceedToCheckoutButton() {
        driver.findElement(proceedToCheckoutButtonLocator).click();
        return new CheckoutPage(driver);
    }
}
