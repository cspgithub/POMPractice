
package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.DriverManager;
import exception.PropertyNotFoundException;
import reports.ExtentLogger;
import utility.Log;
import utility.Utilities;

public class Dashboard extends SelemiumAction {

	private By attendanceLink = By.xpath("//a[contains(text(),'Attendance')and @class='thumb']");
	private By timesheetLink = By.xpath("//a[contains(text(),'TimeSheet')and @class='thumb']");
	private By coforgeTimecardLink = By.xpath("//*[@id=\"ctl00_hlnkTimeCard\"]");
	private By coforgeTimecardSaveButton = By.xpath("//*[@id=\"btnSave\"]");

	private By actionWindow = By.xpath("//*[@id=\"myModal\"]/div/div");
	private By submitButton = By.xpath("//*[@id=\"btnsubmit\"]");
	private By actionWindowCloseButton = By.xpath("//*[@id=\"myModal\"]/div/div/div[3]/button");
	private By vaccinationMessage = By.xpath(
			"//div[@class='chat-panel panel panel-success']//b[text()='*It is mandatory to fill the vaccination form and update it as your status changes.']");
	private By self = By
			.xpath("//div[@id='SmQuestion']//h4[@class='modal-title']/b[contains(text(),' Self-declaration ')]");
	private By optioninSelfDeclareModal = By.xpath(
			"//div[@id='SmQuestion']//following::div[@id='div_RequestType']/table/tbody//td//span[text()='Work from home']/preceding-sibling::input[@type='radio']");
	private By dropdownParentActivity = By.xpath("//select[@class='selectActivity  select2 narrow wrap select2-hidden-accessible']");
	
	private static String status;

	public boolean dashboardloaded() {
		String expected = "https://iengage.coforgetech.com/ess2/HomePage/Welcome";
		return getURL().equalsIgnoreCase(expected);
	}

	public boolean verifingAttendanceLinkInDashboard(String actionName) {
		dashboardloaded();
		if (elementIsPresent(vaccinationMessage)) {
			By saveButton = By.xpath("//*[@id=\"btnSave\"]");
			jsClick(saveButton, "save button in vaccine screen clicked successfully");
			By popupCloseButton = By.xpath("//div[@class='modal-content']//*[@id=\"popupalert1\"]/button");
			click(popupCloseButton, "close button in popuscreen clicked successfully");
		}

		if (elementIsPresent(self)) {
			click(optioninSelfDeclareModal, "WFH radio button");
			click(submitButton, "submit button of selfdeclare modal");
			sleep(1000);
			acceptAlert("Accept");// to test it again
			sleep(2000);
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
					// ACTIONWINDOW NOT PRESENT
				}
			}
		}
		if (elementIsPresent(actionWindowCloseButton)) {
			click(actionWindowCloseButton, "close button in Action Modal");
		}

		highLightWebElement(attendanceLink);
		return getWebElement(attendanceLink).isDisplayed();
	}

	public void verifyAttendanceMarkedForDay() {
		click(attendanceLink, "link Attendance in dashboard");
		actionOnAttendanceTab();
	}

	public void markHrsForCurrentDay() {
		click(timesheetLink, "link timesheet in dashboard");
		actionOnTimesheetTab();
	}

	public int actionOnItemInActionsModal(By by) {
		getWebElement(by).click();
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		return newTab.size();
	}

	public void actionOnAttendanceTab() {
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
		//String currentDate = "July 09";
		String a = String.format(
				"//*[@id='ctl00_ContentPlaceHolder1_calAttendance']/tbody//tr/td[@class='aas_Present']//a[@title='%s']/following::td[text()='Present']",
				currentDate);
		By markedDate = By.xpath(a);
		try {
			status = getWebElement(markedDate).getText();
			ExtentLogger.pass("attendamce marked successfully for " + currentDate, "yes");
		} catch (Exception e) {
			//ExtentLogger.fail("attendance not marked for : " + currentDate +" as its being Sunday/Saturday or Public Holiday,", "yes");
			throw new PropertyNotFoundException("Please mark attendance for valid day/date as " + currentDate +" is either Sunday/Saturday or Holiday");
		}
		DriverManager.getDriver().close();
		// back to dashboard
		DriverManager.getDriver().switchTo().window(oldTab);

	}

	public String attendanceStatus() {
		return status;
	}

	public void actionOnTimesheetTab() {

		// considering that there is only one tab opened in that point.
		String oldTab = DriverManager.getDriver().getWindowHandle();
		// boolean foundElement = false;
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		DriverManager.getDriver().switchTo().window(newTab.get(0));
		// Do what you want here, you are in the new tab
		click(coforgeTimecardLink, "link Coforge Timecard in dashboard");
		DriverManager.getDriver().close();
		ArrayList<String> newTab1 = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		newTab1.remove(oldTab);
		// change focus to new tab
		DriverManager.getDriver().switchTo().window(newTab1.get(0));
		String date = Utilities.formatCurrentLocalDateForTimesheet();
		//String date = "12-Jul-2021";
		String b = String.format(
				"//*[@id=\"theadTimesheetModal\"]/tr[1]/th[2]/following::table[@id='datatableTimesheetModal']/tbody/tr[1]//td/input[@data-timesheetdate='%s']",
				date);
		By blankCells = By.xpath(b);
		try {
			selectFromDropdown(dropdownParentActivity, 2);
			type(blankCells, "8", "hrs in textbox");
			click(coforgeTimecardSaveButton, "button Save in timecard footer");
			Log.info("timesheet updated for the" + date);
		} catch (Exception e) {
			throw new PropertyNotFoundException("timesheet not updated for the :" + date);
		}

	}

// DriverManager.getDriver().close();
// DriverManager.getDriver().switchTo().window(newTab1.get(0));
// DriverManager.getDriver().close();
// back to dashboard
// DriverManager.getDriver().switchTo().window(oldTab);
// return getWebElement(markedDate).isDisplayed();

}
