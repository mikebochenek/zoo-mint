package zoo.mb.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ResebotTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		baseUrl = "http://www.resebot.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAPIRecordings() throws Exception {
		driver.get(baseUrl + "/api/recordings");
		try {
			//System.out.println(driver.getPageSource());
			assertTrue(driver.getPageSource().contains("\"id\":"));
		} catch (Error e) {
			fail(e.getMessage());
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void testAPIPrompts() throws Exception {
		driver.get(baseUrl + "/api/prompts");
		try {
			assertTrue(driver.getPageSource().contains("\"id\":"));
		} catch (Error e) {
			fail(e.getMessage());
			verificationErrors.append(e.toString());
		}
	}

	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
