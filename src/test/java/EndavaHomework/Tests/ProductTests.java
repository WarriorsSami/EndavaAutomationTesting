package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.DriverFactory;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.ProductPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTests {
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
        sut = new ProductPage(driver);
    }

    @Test
    public void GivenCustomProductPage_WhenAddToBasketButtonIsClicked_ThenViewBasketButtonIsDisplayed() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);

        // When
        ((ProductPage) sut).clickAddToBasketButton();

        // Then
        assertTrue(((ProductPage) sut).isViewBasketButtonDisplayed());
    }

    @Test
    public void GivenCustomProductPage_WhenViewBasketButtonIsClickedAfterProductIsAddedToBasket_ThenRedirectsToBasketPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();

        // When
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();

        // Then
        assertTrue(sutBasketPage.getCurrentUrl().contains(sut.getConfigProperty(ConfigProperties.PageUrls.BASKET_URL)));
    }
}
