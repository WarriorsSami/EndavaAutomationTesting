package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.DriverFactory;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.BasketPage;
import EndavaHomework.Pom.Pages.ProductPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketTests {
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
    public void GivenCustomProductInBasket_WhenBasketPageIsAccessed_ThenTotalIsGreaterThanSubtotal() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();

        // When
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();

        // Then
        assertTrue(((BasketPage) sutBasketPage).getTotalPrice() > ((BasketPage) sutBasketPage).getSubtotalPrice());
    }

    @Test
    public void GivenCustomProductInBasket_WhenProceedToCheckoutButtonIsClicked_ThenRedirectsToCheckoutPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();

        // When
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();

        // Then
        assertEquals(sutCheckoutPage.getCurrentUrl(), sut.getConfigProperty(ConfigProperties.PageUrls.CHECKOUT_URL));
    }
}
