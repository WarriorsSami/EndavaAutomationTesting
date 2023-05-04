package EndavaHomework.Pom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ShopPage extends BasePage {
    private final By orderBySelectLocator = By.xpath("//*[@id=\"content\"]/form/select");
    private final By productListLocator = By.xpath("//*[@id=\"content\"]/ul");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void selectOrderBy(String orderBy) {
        try {
            var selectOrderBy = new Select(driver.findElement(orderBySelectLocator));
            selectOrderBy.selectByValue(orderBy);
        } catch (Exception e) {
            skipAds();
            var selectOrderBy = new Select(driver.findElement(orderBySelectLocator));
            selectOrderBy.selectByValue(orderBy);
        }
    }

    public WebElement getProductListItemByPosition(int position) {
        return driver.findElement(productListLocator).findElements(By.xpath("./li")).get(position);
    }

    public String getProductListItemNameByPosition(int position) {
        return getProductListItemByPosition(position).findElement(By.xpath("./a/h3")).getText();
    }

    public ProductPage clickProductListItemByPosition(int position) {
        try {
            WebElement productListItem = getProductListItemByPosition(position);
            productListItem.click();
        } catch (Exception e) {
            skipAds();
            WebElement productListItem = getProductListItemByPosition(position);
            productListItem.click();
        }

        return new ProductPage(driver);
    }
}
