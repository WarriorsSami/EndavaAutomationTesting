package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private final By newArrivalsLocator = By
            .xpath("//*[@id=\"themify_builder_content-22\"]/div[2]/div/div/div/div/div[2]");
    private final By shopMenuButtonLocator = By
            .xpath("//*[@id=\"menu-item-40\"]/a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public int getNewArrivalsCount() {
        return driver
                .findElement(newArrivalsLocator)
                .findElements(By.xpath("./div"))
                .size();
    }

    public ShopPage clickMenuShopButton() {
        // implicitly wait for the shop menu button to be clickable
        WebElement shopButton = driver.findElement(shopMenuButtonLocator);
        shopButton.click();

        return new ShopPage(driver);
    }
}
