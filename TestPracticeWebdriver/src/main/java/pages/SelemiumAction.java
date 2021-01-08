package pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.DriverManager;

public class SelemiumAction {

	WebDriverWait wait;

	protected void sleep(long ms) {
		try {
			Thread.sleep(ms);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected WebElement getWebElement(By by) {
		wait = new WebDriverWait(DriverManager.getDriver(), 40);
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	protected void click(By by) {
		getWebElement(by).click();

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

}
