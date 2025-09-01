package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(Constants.BASE_URL);
		Reporter.log("Step 1: Browser launched and navigated to TutorialNinja homepage", true);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		Reporter.log("Step 6: Browser closed", true);
	}
}
