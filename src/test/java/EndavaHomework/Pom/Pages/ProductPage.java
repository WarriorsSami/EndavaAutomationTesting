package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private final By addToBasketButtonLocator = By.xpath("//*[@id=\"product-165\"]/div[2]/form/button");
    private final By viewBasketButtonLocator = By.xpath("//*[@id=\"content\"]/div[1]/a");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToBasketButton() {
        driver.findElement(addToBasketButtonLocator).click();
    }

    public boolean isViewBasketButtonDisplayed() {
        return driver.findElement(viewBasketButtonLocator).isDisplayed();
    }

    public BasketPage clickViewBasketButton() {
        driver.findElement(viewBasketButtonLocator).click();
        return new BasketPage(driver);
    }
}
