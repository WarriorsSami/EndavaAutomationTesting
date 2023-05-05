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

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTests {
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
    public void GivenCustomProductInBasket_WhenFillingAllTheRequiredBillingFieldsInCheckoutForStateWithOptionalCounty_ThePaymentMethod_And_PlaceOrderButtonIsClicked_ThenRedirectsToOrderReceivedPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();

        // When
        sutCheckoutPage.fillBillingDetailsForCountryWithOptionalCounty();
        assertTrue(sutCheckoutPage.isStateInputDisplayed());
        assertTrue(sutCheckoutPage.isPostcodeInputDisplayed());
        var sutOrderReceivedPage = sutCheckoutPage.clickPlaceOrderButton(true, true);

        // Then
        assertTrue(sutOrderReceivedPage.getCurrentUrl().contains(sut.getConfigProperty(ConfigProperties.PageUrls.ORDER_RECEIVED_URL)));
    }

    @Test
    public void GivenCustomProductInBasket_WhenFillingAllTheRequiredBillingFieldsInCheckoutForStateWithoutPostcode_ThePaymentMethod_And_PlaceOrderButtonIsClicked_ThenRedirectsToOrderReceivedPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();

        // When
        sutCheckoutPage.fillBillingDetailsForCountryWithoutPostcodeInput();
        assertTrue(sutCheckoutPage.isStateInputDisplayed());
        assertFalse(sutCheckoutPage.isPostcodeInputDisplayed());
        var sutOrderReceivedPage = sutCheckoutPage.clickPlaceOrderButton(true, true);

        // Then
        assertTrue(sutOrderReceivedPage.getCurrentUrl().contains(sut.getConfigProperty(ConfigProperties.PageUrls.ORDER_RECEIVED_URL)));
    }

    @Test
    public void GivenCustomProductInBasket_WhenFillingAllTheRequiredBillingFieldsInCheckoutForStateWithRequiredCounty_ThePaymentMethod_And_PlaceOrderButtonIsClicked_ThenRedirectsToOrderReceivedPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();

        // When
        sutCheckoutPage.fillBillingDetailsForCountryWithRequiredStateAsInput();
        assertTrue(sutCheckoutPage.isStateInputDisplayed());
        var sutOrderReceivedPage = sutCheckoutPage.clickPlaceOrderButton(true, true);

        // Then
        assertTrue(sutOrderReceivedPage.getCurrentUrl().contains(sut.getConfigProperty(ConfigProperties.PageUrls.ORDER_RECEIVED_URL)));
    }

    @Test
    public void GivenCustomProductInBasket_WhenNotFillingAnyRequiredBillingFieldAndOrderIsPlaced_ThenFlashMessagesAreDisplayed() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.PRODUCT_URL);
        ((ProductPage) sut).clickAddToBasketButton();
        var sutBasketPage = ((ProductPage) sut).clickViewBasketButton();
        var sutCheckoutPage = sutBasketPage.clickProceedToCheckoutButton();

        // When
        var sutOrderReceivedPage = sutCheckoutPage.clickPlaceOrderButton(false, false);

        // Then
        assertTrue(sutCheckoutPage.isFlashErrorsListDisplayed());
        assertEquals(sutCheckoutPage.getFlashErrorsListSize(), 7);

        var flashErrorsNameList = new String[]{"First Name", "Last Name", "Email Address", "Phone", "Address", "Town / City", "ZIP"};
        for (var name: flashErrorsNameList) {
            assertTrue(sutCheckoutPage.checkIfFlashErrorsListContainsText(name));
        }

        assertTrue(sutOrderReceivedPage.getCurrentUrl().contains(sut.getConfigProperty(ConfigProperties.PageUrls.CHECKOUT_URL)));
    }
}
