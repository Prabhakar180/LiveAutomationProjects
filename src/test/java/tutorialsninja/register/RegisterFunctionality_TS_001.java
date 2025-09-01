package tutorialsninja.register;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RegisterFunctionality_TS_001 extends BaseTest {
	Methods method = new Methods();

	@Test(description = "Verify Registering an Account by providing only the Mandatory fields")
	public void TC_RF_001() {
		// Generate test data
		String firstName = Methods.generateRandomAlphabets(10);
		String lastName = Methods.generateRandomAlphabets(10);
		String email = Methods.generateRandomEmail();
		String telephone = Methods.generateRandomNumber(10);
		String password = Methods.generateRandomAlphanumeric(10);

		// Verify Registering an Account by providing only the Mandatory fields
		method.VerifyRegisterAccountWithMandatoryFields(driver, firstName, lastName, email, telephone, password);
	}

	@Test(description = "Verify Registering an Account by providing all the fields")
	public void TC_RF_003() {
		// Generate test data
		String firstName = Methods.generateRandomAlphabets(10);
		String lastName = Methods.generateRandomAlphabets(10);
		String email = Methods.generateRandomEmail();
		String telephone = Methods.generateRandomNumber(10);
		String password = Methods.generateRandomAlphanumeric(10);

		// Verify Registering an Account by providing all the fields
		method.VerifyRegisterAccountWithAllFields(driver, firstName, lastName, email, telephone, password);
	}

	@Test(description = "Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit")
	public void TC_RF_004() {
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.MyAccount)).click();
		driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.Register)).click();
		Reporter.log("Step 2: Navigated to Registration page", true);

		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.Continue)).click();
		Reporter.log("Step 3: Clicked 'Continue' to submit the form without entering any fields", true);

		String WarningMessage = method.getInnerText(driver, LocatorFactory.getByXpath(Locators.XPaths.DangerAlert));
		Assert.assertTrue(WarningMessage.equals(Constants.expectedPrivacyPolicyWarningWarning),
				"Assertion failed!\nExpected: " + Constants.expectedPrivacyPolicyWarningWarning + "\nActual: "
						+ WarningMessage);
		List<String> expectedTexts = Arrays.asList(Constants.expectedFirstNameWarning,
				Constants.expectedLastNameWarning, Constants.expectedEmailWarning, Constants.expectedTelephoneWarning,
				Constants.expectedPasswordWarning);
		List<String> actualTexts = method.getInnerTexts(driver,
				LocatorFactory.getByclassName(Locators.ClassName.DangerText));
		Assert.assertTrue(actualTexts.containsAll(expectedTexts),
				"Assertion failed!\nExpected: " + expectedTexts + "\nActual: " + actualTexts);
		Reporter.log("Step 4: Validated all mandatory field warning messages", true);
	}

	@Test(description = "Verify Registering an Account when 'Yes' option is selected for Newsletter field")
	public void TC_RF_005() {
		// Generate test data
		String firstName = Methods.generateRandomAlphabets(10);
		String lastName = Methods.generateRandomAlphabets(10);
		String email = Methods.generateRandomEmail();
		String telephone = Methods.generateRandomNumber(10);
		String password = Methods.generateRandomAlphanumeric(10);

		// Verify Registering an Account by providing all the fields
		method.VerifyRegisterAccountWithAllFields(driver, firstName, lastName, email, telephone, password);

		// Click on 'Subscribe/Unsubscribe to newsletter' option
		driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.SubscribeOrUnsubscribeNewsletter)).click();
		WebElement radio = driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.Newsletter));
		Assert.assertTrue(radio.isSelected(), "'Yes' option in Newsletter is not selected!");
		Reporter.log("Step 6: Clicked on 'Subscribe to newsletter' option and validated 'Yes' is selected", true);
	}

}
