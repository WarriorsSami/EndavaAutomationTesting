package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.HomePage;
import EndavaHomework.Pom.Pages.ShopPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingTests {
    private static WebDriver driver;
    private BasePage sut;

    @BeforeAll
    public static void setupDriver() {
        var options = new ChromeOptions();
        options.addArguments("load-extension=" + "src/test/resources/adblock/5.6.0_0");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    public void setup() {
        sut = new HomePage(driver);
    }

    @AfterEach
    public void tearDown() {
//        sut.close();
    }

    @Test
    public void GivenHomePageIsAccessed_WhenShopMenuButtonIsClickedAndShopPageIsOpenedAndProductIsSelectedAfterFiltering_ThenProductExists() {
        // Given
        var orderBy = "rating";
        var expectedProductName = "Mastering JavaScript";
        var productPosition = 2;
        sut.navigateToUrl(ConfigProperties.PageUrls.HOME_URL);

        // When
        var sutShopPage = ((HomePage) sut).clickMenuShopButton();
        sutShopPage.selectOrderBy(orderBy);
        var productItemName = sutShopPage.getProductListItemNameByPosition(productPosition);

        // Then
        assertEquals(expectedProductName, productItemName);
    }

    @Test
    public void GivenShopPageIsAccessed_WhenProductIsClickedAndProductPageIsOpenedAndProductIsAddedToCart_ThenViewCartButtonIsDisplayed() {
        // Given
        var orderBy = "rating";
        var productPosition = 2;
        sut.navigateToUrl(ConfigProperties.PageUrls.SHOP_URL);
        var sutShopPage = new ShopPage(driver);

        // When
        sutShopPage.selectOrderBy(orderBy);
        var sutProductPage = sutShopPage.clickProductListItemByPosition(productPosition);
        sutProductPage.clickAddToBasketButton();

        // Then
        assertTrue(sutProductPage.isViewBasketButtonDisplayed());
    }
}
