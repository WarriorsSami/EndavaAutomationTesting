package EndavaHomework.Tests;

import EndavaHomework.Pom.ConfigProperties;
import EndavaHomework.Pom.Pages.BasePage;
import EndavaHomework.Pom.Pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTests {
    private static WebDriver driver;
    private BasePage sut;

    @BeforeAll
    public static void setupDriver() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void setup() {
        sut = new HomePage(driver);
    }

    @AfterEach
    public void tearDown() {
        sut.close();
    }

    @Test
    public void GivenHomePageIsAccessed_WhenHomePageElementsAreFullyRendered_ThenNewArrivalsSectionContainsNElements() {
        // Given
        sut.navigateToUrl(ConfigProperties.PageUrls.HOME_URL);

        // When
        var actualNewArrivalsCount = ((HomePage) sut).getNewArrivalsCount();

        // Then
        assertEquals(sut.getConfigProperty(ConfigProperties.PageUrls.HOME_URL), sut.getCurrentUrl());
        assertEquals(3, actualNewArrivalsCount);
    }
}
