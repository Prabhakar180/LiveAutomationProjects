package tutorialsninja.register;

import org.openqa.selenium.By;

public class LocatorFactory {

	public static By getById(String id) {
		return By.id(id);
	}

	public static By getByName(String name) {
		return By.name(name);
	}

	public static By getByCss(String cssSelector) {
		return By.cssSelector(cssSelector);
	}

	public static By getByXpath(String xpath) {
		return By.xpath(xpath);
	}

	public static By getByLinkText(String linkText) {
		return By.linkText(linkText);
	}
	
	public static By getByclassName(String className) {
		return By.className(className);
	}

}
