package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.DriverFactory;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.ShopPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTests {
    private static WebDriver driver;
    private static BasePage sut;

    @BeforeAll
    public static void setupDriver() {
        driver = DriverFactory.getDriver("chrome");
    }

    @AfterAll
    public static void tearDown() {
        sut.close();
    }

    @BeforeEach
    public void setup() {
        sut = new ShopPage(driver);
    }

    @Test
    public void GivenShopPage_WhenCustomFilterIsApplied_ThenProductIsPlacedKth() {
        // Given
        var orderBy = "rating";
        var expectedProductName = "Mastering JavaScript";
        var productPosition = 2;
        sut.navigateToUrl(ConfigProperties.PageUrls.SHOP_URL);

        // When
        ((ShopPage) sut).selectOrderBy(orderBy);
        var productItemName = ((ShopPage) sut).getProductListItemNameByPosition(productPosition);

        // Then
        assertEquals(expectedProductName, productItemName);
    }

    @Test
    public void GivenShopPage_WhenProductIsClicked_ThenRedirectsToProductPage() {
        // Given
        var orderBy = "rating";
        var productPosition = 2;
        sut.navigateToUrl(ConfigProperties.PageUrls.SHOP_URL);

        // When
        ((ShopPage) sut).selectOrderBy(orderBy);
        var sutProductPage = ((ShopPage) sut).clickProductListItemByPosition(productPosition);

        // Then
        assertEquals(sut.getConfigProperty(ConfigProperties.PageUrls.PRODUCT_URL), sutProductPage.getCurrentUrl());
    }
}
