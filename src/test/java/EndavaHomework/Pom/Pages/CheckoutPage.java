package EndavaHomework.Pom.Pages;

import EndavaHomework.Pom.ConfigProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By firstNameInputLocator = By.id("billing_first_name");
    private final By lastNameInputLocator = By.id("billing_last_name");
    private final By emailAddressInputLocator = By.id("billing_email");
    private final By phoneInputLocator = By.id("billing_phone");
    private final By countrySelectLocator = By.id("billing_country");
    private final By addressInputLocator = By.id("billing_address_1");
    private final By cityInputLocator = By.id("billing_city");
    private final By postcodeInputLocator = By.id("billing_postcode");
    private final By cashOnDeliveryRadioButtonLocator = By.id("payment_method_cod");
    private final By placeOrderButtonLocator = By.id("place_order");

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

    public void selectCountry() {
        driver.findElement(countrySelectLocator).sendKeys(getConfigProperty(ConfigProperties.BillingDetails.COUNTRY));
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

    public void clickCashOnDeliveryRadioButton() {
        driver.findElement(cashOnDeliveryRadioButtonLocator).click();
    }

    public OrderReceivedPage clickPlaceOrderButton() {
        driver.findElement(placeOrderButtonLocator).click();
        return new OrderReceivedPage(driver);
    }
}
