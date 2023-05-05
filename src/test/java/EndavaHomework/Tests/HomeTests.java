package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.DriverFactory;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTests {
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
        sut = new HomePage(driver);
    }

    @Test
    public void GivenHomePage_WhenPageLoads_ThenNewArrivalsSectionContains3Elements() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.HOME_URL);

        // When
        var actualNewArrivalsCount = ((HomePage) sut).getNewArrivalsCount();

        // Then
        assertEquals(sut.getConfigProperty(ConfigProperties.PageUrls.HOME_URL), sut.getCurrentUrl());
        assertEquals(3, actualNewArrivalsCount);
    }

    @Test
    public void GivenHomePage_WhenShopButtonIsClicked_ThenRedirectToShopPage() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.HOME_URL);

        // When
        var sutShopPage = ((HomePage) sut).clickMenuShopButton();

        // Then
        assertEquals(sut.getConfigProperty(ConfigProperties.PageUrls.SHOP_URL), sutShopPage.getCurrentUrl());
    }
}
