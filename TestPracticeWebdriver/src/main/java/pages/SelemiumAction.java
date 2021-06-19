package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import base.DriverManager;
import reports.ExtentLogger;

public class SelemiumAction {

	protected void sleep(long ms) {
		try {
			Thread.sleep(ms);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected WebElement getWebElement(By by) {
		// wait = new WebDriverWait(DriverManager.getDriver(), 15);
		// return wait.until(ExpectedConditions.presenceOfElementLocated(by));

		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverManager.getDriver()).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(5));//.ignoring(NoSuchElementException.class)

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return webElement;
	}

	protected void click(By by) {
		getWebElement(by).click();
		//ExtentLogger.pass("clicked sussefully");

	}

	protected void type(By by, String value) {
		clearWebField(getWebElement(by));
		getWebElement(by).sendKeys(value);

	}

	protected void clearWebField(WebElement element) {
		while (!element.getAttribute("value").equals("")) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void acceptAlert(String action) {
		sleep(100);
		Alert alert = (DriverManager.getDriver().switchTo().alert());
		switch (action) {
		case "Accept":
			alert.accept();
			break;
		case "Dismiss":
			alert.dismiss();
			break;

		default:
			break;
		}

	}

	public List<WebElement> getListOfWebElementByString(String str) {
		return DriverManager.getDriver().findElements(By.xpath(str));
	}

	public List<WebElement> getListOfWebElementByElement(By by) {
		return DriverManager.getDriver().findElements(by);
	}

	public void highLightWebElement(By by) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", getWebElement(by));
	}

	public String formatCurrentLocalDate() {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd"));
		return date;

	}

}
