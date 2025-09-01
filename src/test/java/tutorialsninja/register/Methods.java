package tutorialsninja.register;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class Methods {

	public static String generateRandomAlphabets(int length) {
		StringBuilder result = new StringBuilder();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			result.append(alphabet.charAt(new Random().nextInt(alphabet.length())));
		}
		return result.toString();
	}

	public static String generateRandomEmail() {
		return generateRandomAlphabets(10) + "@gmail.com";
	}

	public static String generateRandomNumber(int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(new Random().nextInt(10));
		}
		return result.toString();
	}

	public static String generateRandomAlphanumeric(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(characters.charAt(new Random().nextInt(characters.length())));
		}
		return result.toString();
	}

	public void selectRadioButtonAndValidate(WebDriver driver, By locator) {
		WebElement radio = driver.findElement(locator);
		if (!radio.isSelected()) {
			radio.click();
		}
		// Post-click validation
		if (!radio.isSelected()) {
			throw new AssertionError("Radio button at " + locator.toString() + " was not selected after clicking.");
		}
	}

	/**
	 * Returns a list of inner texts from all elements matching the given locator.
	 *
	 * @param locator By object (XPath, CSS, ID, etc.)
	 * @return List of trimmed inner text strings
	 */
	public List<String> getInnerTexts(WebDriver driver, By locator) {
		List<WebElement> elements = driver.findElements(locator);
		List<String> texts = new ArrayList<>();
		for (WebElement element : elements) {
			texts.add(element.getText().trim());
		}
		return texts;
	}

	public String getInnerText(WebDriver driver, By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element.getText().trim();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + locator);
			return "";
		}
	}

	/**
	 * Verify Registering an Account by providing only the Mandatory fields
	 * 
	 * @param firstName First Name of the user
	 * @param lastName  Last Name of the user
	 * @param email     Email address
	 * @param telephone Telephone number
	 * @param password  Password to be set
	 */
	public void VerifyRegisterAccountWithMandatoryFields(WebDriver driver, String firstName, String lastName,
			String email, String telephone, String password) {
		// Navigate to Registration Page
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.MyAccount)).click();
		driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.Register)).click();
		Reporter.log("Step 2: Navigated to Registration page", true);

		// Fill Mandatory Fields
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.FIRST_NAME)))
				.sendKeys(firstName);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.LAST_NAME)))
				.sendKeys(lastName);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.EMAIL)))
				.sendKeys(email);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Telephone)))
				.sendKeys(telephone);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Password)))
				.sendKeys(password);
		driver.findElement(LocatorFactory
				.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Confirm_Password)))
				.sendKeys(password);
		Reporter.log("Step 3: Registration form filled successfully", true);

		// Agree to Terms and Submit
		driver.findElement(LocatorFactory.getByName(Locators.Names.Agree)).click();
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.Continue)).click();
		Reporter.log("Step 4: Clicked Continue to submit the form", true);

		// Verify Account Creation Success
		Assert.assertEquals(driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.AccountCreated)).getText(),
				Constants.AccountCreated_SUCCESS, "Account creation success message did not match!");
		Reporter.log("Step 5: Account registered successfully - success message displayed", true);

		// Validate proper details should be displayed on the page
		String[] expectedDetails = { Constants.expectedProperDetailsOne, Constants.expectedProperDetailsTwo,
				Constants.expectedProperDetailsThree, Constants.expectedProperDetailsFour };
		String actualProperDetails = driver.findElement(LocatorFactory.getById(Locators.Ids.Content)).getText();
		for (String expected : expectedDetails) {
			Assert.assertTrue(actualProperDetails.contains(expected),
					"Expected text not found: " + expected + "\nActual content: " + actualProperDetails);
			Reporter.log("Validated presence of: " + expected, true);
		}

		// Final Confirmation and Navigation
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.ContinueButton)).click();
		Assert.assertTrue(
				driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.EditYourAccount)).isDisplayed(),
				"Edit Your Account' link is NOT displayed!");
	}

	/**
	 * Verify Registering an Account by providing all the fields
	 * 
	 * @param firstName First Name of the user
	 * @param lastName  Last Name of the user
	 * @param email     Email address
	 * @param telephone Telephone number
	 * @param password  Password to be set
	 */
	public void VerifyRegisterAccountWithAllFields(WebDriver driver, String firstName, String lastName, String email,
			String telephone, String password) {
		// Navigate to Registration Page
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.MyAccount)).click();
		driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.Register)).click();
		Reporter.log("Step 2: Navigated to Registration page", true);

		// Fill Mandatory Fields
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.FIRST_NAME)))
				.sendKeys(firstName);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.LAST_NAME)))
				.sendKeys(lastName);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.EMAIL)))
				.sendKeys(email);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Telephone)))
				.sendKeys(telephone);
		driver.findElement(
				LocatorFactory.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Password)))
				.sendKeys(password);
		driver.findElement(LocatorFactory
				.getById(String.format(LocatorTemplates.INPUT_ID_TEMPLATE, Locators.Ids.Confirm_Password)))
				.sendKeys(password);
		Reporter.log("Step 3: Registration form filled successfully", true);

		// Select News letter, Agree to Terms and Submit
		selectRadioButtonAndValidate(driver, By.xpath(Locators.XPaths.Newsletter));
		driver.findElement(LocatorFactory.getByName(Locators.Names.Agree)).click();
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.Continue)).click();
		Reporter.log("Step 4: Clicked Continue to submit the form", true);

		// Validate proper details should be displayed on the page
		String[] expectedDetails = { Constants.expectedProperDetailsOne, Constants.expectedProperDetailsTwo,
				Constants.expectedProperDetailsThree, Constants.expectedProperDetailsFour };
		String actualProperDetails = driver.findElement(LocatorFactory.getById(Locators.Ids.Content)).getText();
		for (String expected : expectedDetails) {
			Assert.assertTrue(actualProperDetails.contains(expected),
					"Expected text not found: " + expected + "\nActual content: " + actualProperDetails);
			Reporter.log("Validated presence of: " + expected, true);
		}

		// Final Confirmation and Navigation
		driver.findElement(LocatorFactory.getByXpath(Locators.XPaths.ContinueButton)).click();
		Assert.assertTrue(
				driver.findElement(LocatorFactory.getByLinkText(Locators.linkText.EditYourAccount)).isDisplayed(),
				"Edit Your Account' link is NOT displayed!");
	}

}
