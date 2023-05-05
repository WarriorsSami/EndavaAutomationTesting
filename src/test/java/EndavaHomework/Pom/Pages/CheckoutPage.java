package EndavaHomework.Pom.Pages;

import EndavaHomework.Pom.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    private final By firstNameInputLocator = By.id("billing_first_name");
    private final By lastNameInputLocator = By.id("billing_last_name");
    private final By emailAddressInputLocator = By.id("billing_email");
    private final By phoneInputLocator = By.id("billing_phone");
    private final By countrySelectLocator = By.id("s2id_billing_country");
    private final By countryInputLocator = By.id("s2id_autogen1_search");
    private final By countryResultListLocator = By.id("select2-results-1");
    private final By addressInputLocator = By.id("billing_address_1");
    private final By cityInputLocator = By.id("billing_city");
    private final By stateSelectLocator = By.xpath("//*[@id=\"s2id_billing_state\"]/a");
    private final By stateSelectInputLocator = By.xpath("//*[@id=\"select2-drop\"]/div/input");
    private final By stateResultListLocator = By.xpath("//*[@id=\"select2-drop\"]/ul");
    private final By stateInputLocator = By.xpath("//*[@id=\"billing_state\"]");
    private final By postcodeInputLocator = By.id("billing_postcode");
    private final By cashOnDeliveryRadioButtonLocator = By.xpath("//*[@id=\"payment\"]/ul/li[3]");
    private final By placeOrderButtonLocator = By.id("place_order");
    private final By flashErrorsListLocator = By.xpath("//*[@id=\"page-35\"]/div/div[1]/form[3]/ul");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillFirstNameInput() {
        driver.findElement(firstNameInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.FIRST_NAME));
    }

    public void fillLastNameInput() {
        driver.findElement(lastNameInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.LAST_NAME));
    }

    public void fillEmailAddressInput() {
        driver.findElement(emailAddressInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.EMAIL_ADDRESS));
    }

    public void fillPhoneInput() {
        driver.findElement(phoneInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.PHONE));
    }

    public void selectStateAsSelectListItem() {
        driver.findElement(stateSelectLocator).click();
        driver.findElement(stateSelectInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.STATE_ALABAMA));
        driver.findElement(stateResultListLocator).findElements(By.xpath("./li")).get(0).click();
    }

    public void selectStateAsInput() {
        driver.findElement(stateInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTY_AMERICAN_SAMOA));
    }

    public void selectCountryWithOptionalCounty() {
        driver.findElement(countrySelectLocator).click();
        driver.findElement(countryInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTRY_ROMANIA));
        driver.findElement(countryResultListLocator).findElements(By.xpath("./li")).get(0).click();
    }

    public void selectCountryWithRequiredStateAsSelectListItem() {
        driver.findElement(countrySelectLocator).click();
        driver.findElement(countryInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTRY_UNITED_STATES));
        driver.findElement(countryResultListLocator).findElements(By.xpath("./li")).get(0).click();

        selectStateAsSelectListItem();
    }

    public void selectCountryWithRequiredStateAsInput() {
        driver.findElement(countrySelectLocator).click();
        driver.findElement(countryInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTRY_BAHAMAS));
        driver.findElement(countryResultListLocator).findElements(By.xpath("./li")).get(0).click();

        selectStateAsInput();
    }

    public void selectCountryWithoutPostcodeInput() {
        driver.findElement(countrySelectLocator).click();
        driver.findElement(countryInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTRY_BAHAMAS));
        driver.findElement(countryResultListLocator).findElements(By.xpath("./li")).get(0).click();

        selectStateAsInput();
    }

    public void fillAddressInput() {
        driver.findElement(addressInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.ADDRESS));
    }

    public void fillCityInput() {
        driver.findElement(cityInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.CITY));
    }

    public void fillPostcodeInput() {
        driver.findElement(postcodeInputLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.POST_CODE));
    }

    public boolean isPostcodeInputDisplayed() {
        return driver.findElement(postcodeInputLocator).isDisplayed();
    }

    public boolean isStateInputDisplayed() {
        return driver.findElement(stateInputLocator).isDisplayed();
    }

    public boolean isFlashErrorsListDisplayed() {
        return driver.findElement(flashErrorsListLocator).isDisplayed();
    }

    public int getFlashErrorsListSize() {
        return driver.findElement(flashErrorsListLocator).findElements(By.xpath("./li")).size();
    }

    public boolean checkIfFlashErrorsListContainsText(String text) {
        for (WebElement element : driver.findElement(flashErrorsListLocator).findElements(By.xpath("./li"))) {
            if (element.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void clickCashOnDeliveryRadioButton() {
        try {
            driver.findElement(cashOnDeliveryRadioButtonLocator).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(cashOnDeliveryRadioButtonLocator).click();
        }
    }

    public void fillBillingDetailsForCountryWithOptionalCounty() {
        fillFirstNameInput();
        fillLastNameInput();
        fillEmailAddressInput();
        fillPhoneInput();
        selectCountryWithOptionalCounty();
        fillAddressInput();
        fillCityInput();
        fillPostcodeInput();
        clickCashOnDeliveryRadioButton();
    }

    public void fillBillingDetailsForCountryWithRequiredStateAsSelectListItem() {
        fillFirstNameInput();
        fillLastNameInput();
        fillEmailAddressInput();
        fillPhoneInput();
        selectCountryWithRequiredStateAsSelectListItem();
        fillAddressInput();
        fillCityInput();
        fillPostcodeInput();
        clickCashOnDeliveryRadioButton();
    }

    public void fillBillingDetailsForCountryWithRequiredStateAsInput() {
        fillFirstNameInput();
        fillLastNameInput();
        fillEmailAddressInput();
        fillPhoneInput();
        selectCountryWithRequiredStateAsInput();
        fillAddressInput();
        fillCityInput();
        clickCashOnDeliveryRadioButton();
    }

    public void fillBillingDetailsForCountryWithoutPostcodeInput() {
        fillFirstNameInput();
        fillLastNameInput();
        fillEmailAddressInput();
        fillPhoneInput();
        selectCountryWithoutPostcodeInput();
        fillAddressInput();
        fillCityInput();
        clickCashOnDeliveryRadioButton();
    }

    public OrderReceivedPage clickPlaceOrderButton() {
        driver.findElement(placeOrderButtonLocator).click();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.xpath("//*[@id=\"page-35\"]/div/div[1]/h2")) != null);

        return new OrderReceivedPage(driver);
    }
}
