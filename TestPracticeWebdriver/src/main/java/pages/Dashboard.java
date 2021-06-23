
package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.DriverManager;
import utility.Utilities;

public class Dashboard extends SelemiumAction {

	private By attendanceLink = By.xpath("//a[contains(text(),'Attendance')and @class='thumb']");
	private By actionWindow = By.xpath("//*[@id=\"myModal\"]/div/div");
	private By submitButton = By.xpath("//*[@id=\"btnsubmit\"]");
	private By actionWindowCloseButton = By.xpath("//*[@id=\"myModal\"]/div/div/div[3]/button");
	private By vaccinationMessage = By.xpath(
			"//div[@class='chat-panel panel panel-success']//b[text()='*It is mandatory to fill the vaccination form and update it as your status changes.']");
	private By self = By
			.xpath("//div[@id='SmQuestion']//h4[@class='modal-title']/b[contains(text(),' Self-declaration ')]");
	private By optioninSelfDeclareModal = By.xpath(
			"//div[@id='SmQuestion']//following::div[@id='div_RequestType']/table/tbody//td//span[text()='Work from home']/preceding-sibling::input[@type='radio']");

	
	public boolean dashboardloaded() {
		String expected = "https://iengage.coforgetech.com/ess2/HomePage/Welcome";
		return getURL().equalsIgnoreCase(expected);
	}
	public boolean verifingAttendanceLinkInDashboard(String actionName) {
		if (elementIsPresent(vaccinationMessage)) {
			By saveButton = By.xpath("//*[@id=\"btnSave\"]");
			click(saveButton, "save button in vaccine screen");
			By popupCloseButton = By.xpath("//*[@id=\"popupalert1\"]/button");
			click(popupCloseButton, "close button in popuscreen");
		}

		if (elementIsPresent(self)) {
			click(optioninSelfDeclareModal, "WFH radio button");
			click(submitButton, "submit button of selfdeclare modal");
			sleep(100);
			acceptAlert("Accept");// to test it again
		}

		if (elementIsPresent(actionWindow)) {
			String actionAttendance = String.format(
					"//div[@class='modal-body']/table/tbody//td[contains(text(),'%s')]/following::tr[1]//a",
					actionName);
			By attendanceLink = By.xpath(actionAttendance);
			List<WebElement> listOfItems = getListOfWebElementByElement(attendanceLink);
			for (WebElement webElement : listOfItems) {
				String linkText = webElement.getText().toString().toLowerCase();
				if (linkText.contains(actionName.toLowerCase())) {
					webElement.click();
					By popupAfterclickingAttendanceLink = By
							.xpath("//*[@id=\"myModal\"]/div/div//button[@class='btn btn-primary' and text()='Close']");
					click(popupAfterclickingAttendanceLink, "button after successful attendance");
					break;
				}
			}
		}
		click(actionWindowCloseButton, "close button in Action Modal");
		highLightWebElement(attendanceLink);
		return getWebElement(attendanceLink).isDisplayed();
	}

	public boolean verifyAttendanceMarkedForDay() {
		click(attendanceLink, "link Attendance in dashboard");
		return actionOnAttendanceTab();
	}

	public int actionOnItemInActionsModal(By by) {
		getWebElement(by).click();
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		return newTab.size();
	}

	public void actionOnNewTab(By by) {
		// considering that there is only one tab opened in that point.
		String oldTab = DriverManager.getDriver().getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		DriverManager.getDriver().switchTo().window(newTab.get(0));
		// Do what you want here, you are in the new tab
		DriverManager.getDriver().close();
		// change focus back to old tab
		DriverManager.getDriver().switchTo().window(oldTab);

	}

	public boolean actionOnAttendanceTab() {
		// considering that there is only one tab opened in that point.
		String oldTab = DriverManager.getDriver().getWindowHandle();
		// boolean foundElement = false;
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		DriverManager.getDriver().switchTo().window(newTab.get(0));
		// Do what you want here, you are in the new tab
		By attendanceTable = By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_calAttendance\"]/tbody");
		getWebElement(attendanceTable).isDisplayed();
		String currentDate = Utilities.formatCurrentLocalDate();
		String a = String.format(
				"//*[@id='ctl00_ContentPlaceHolder1_calAttendance']/tbody//tr/td[@class='aas_Present']//a[@title='%s']/following::td[text()='Present']/../..",
				currentDate);
		By markedDate = By.xpath(a);
		highLightWebElement(markedDate);
		return getWebElement(markedDate).isDisplayed();
	}

}
