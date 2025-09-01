package tutorialsninja.register;

public class Locators {

	public static class Ids {
		public static final String FIRST_NAME = "firstname";
		public static final String LAST_NAME = "lastname";
		public static final String EMAIL = "email";
		public static final String Telephone = "telephone";
		public static final String Password = "password";
		public static final String Confirm_Password = "confirm";
		public static final String Content = "content";
	}

	public static class Names {
		public static final String Agree = "agree";
	}

	public static class XPaths {
		public static final String MyAccount = "//span[text()= 'My Account']";
		public static final String Continue = "//input[@value='Continue']";
		public static final String AccountCreated = "//div[@id='common-success']//h1";
		public static final String ContinueButton = "//a[text()='Continue']";
		public static final String Newsletter = "//input[@name='newsletter'][@value='1']";
		public static final String DangerAlert = "//div[@class='alert alert-danger alert-dismissible']";
	}

	public static class CssSelectors {
	}

	public static class ClassName {
		public static final String DangerText = "text-danger";
	}

	public static class linkText {
		public static final String Register = "Register";
		public static final String EditYourAccount = "Edit your account information";
		public static final String SubscribeOrUnsubscribeNewsletter = "Subscribe / unsubscribe to newsletter";
		
	}

}
