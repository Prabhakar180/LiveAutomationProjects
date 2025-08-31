package tutorialsninja.register;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
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

		// Call reusable method with generated data
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

		// Call reusable method with generated data
		method.VerifyRegisterAccountWithAllFields(driver, firstName, lastName, email, telephone, password);
	}

	@Test(description = "Verify proper notification messages are displayed for the mandatory fields, when you don't provide any fields in the 'Register Account' page and submit")
	public void TC_RF_004() {
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.MyAccount)).click();
		driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.Register)).click();
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.Continue)).click();
		String WarningMessage = method.getInnerText(driver, LocatorFactory.getByXpath(Locators.XPaths.DangerAlert));
		Assert.assertTrue(WarningMessage.equals(Constants.expectedPrivacyPolicyWarningWarning),
				"Some expected messages are missing");
		List<String> expectedTexts = Arrays.asList(Constants.expectedFirstNameWarning,
				Constants.expectedLastNameWarning, Constants.expectedEmailWarning, Constants.expectedTelephoneWarning,
				Constants.expectedPasswordWarning);
		List<String> actualTexts = method.getInnerTexts(driver,
				LocatorFactory.getByclassName(Locators.ClassName.DangerText));
		Assert.assertTrue(actualTexts.containsAll(expectedTexts), "Some expected messages are missing");

	}

}
