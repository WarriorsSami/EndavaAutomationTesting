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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderReceivedTests {
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
    public void GivenCustomProductInBasket_WhenFillingAllTheRequiredBillingFieldsInCheckoutAndOrderIsSuccessfullyPlaced_ThenRedirectsToOrderReceivedPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();
        sutCheckoutPage.fillBillingDetailsForCountryWithOptionalCounty();

        // When
        var sutOrderReceivedPage = sutCheckoutPage.clickPlaceOrderButton(true, true);

        // Then
        var expectedPaymentMethod = "Cash on Delivery";
        assertEquals(expectedPaymentMethod, sutOrderReceivedPage.getCashOnDeliveryPaymentMethodFirst());
        assertEquals(expectedPaymentMethod, sutOrderReceivedPage.getCashOnDeliveryPaymentMethodSecond());
    }
}
