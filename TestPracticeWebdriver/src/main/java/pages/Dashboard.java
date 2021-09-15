
package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private By coforgeTimecardCloseButtonAfterSubmission = By
			.xpath("//button[@class='btn btn-primary pull-right ActionClose']");
	private By btnSucessTimeCard = By.id("btnSubmit");
	private By btnAdminconfirmTimecard = By.id("btnAdminconfirm");

	private By actionWindow = By.xpath("//*[@id='myModal']/div/div/div[1]/h4/b[contains(text(),'Actions')]");
	private By submitButton = By.xpath("//*[@id=\"btnsubmit\"]");
	private By actionWindowCloseButton = By.xpath("//*[@id='myModal']/div/div/div[3]/button");
	private By vaccinationMessage = By.xpath(
			"//div[@class='chat-panel panel panel-success']//b[text()='*It is mandatory to fill the vaccination form and update it as your status changes.']");
	private By self = By
			.xpath("//div[@id='SmQuestion']//h4[@class='modal-title']/b[contains(text(),' Self-declaration ')]");
	private By optioninSelfDeclareModal = By.xpath(
			"//div[@id='SmQuestion']//following::div[@id='div_RequestType']/table/tbody//td//span[text()='Work from home']/preceding-sibling::input[@type='radio']");
	private By dropdownParentActivity = By.xpath(
			"//table[@id='datatableTimesheetModal']//tbody[@id='tbodyTimesheetModal']/tr[@class='odd'][1]//select[@class='selectActivity select2 narrow wrap select2-hidden-accessible']");

	private static String status = "";

	private By textBoxAll = By.xpath(
			"//*[@id='theadTimesheetModal']/tr[1]/th[2]/following::table[@id='datatableTimesheetModal']/tbody/tr[1]//td/input[@data-daytype='1']");

	private static Map<String, String> map = new HashMap<>();

	public boolean dashboardloaded() {
		String expected = "https://iengage.coforgetech.com/ess2/HomePage/Welcome";
		return getURL().equalsIgnoreCase(expected);
	}

	public boolean markAttendance(String actionName) {
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
			sleep(2600);
		}

		if (elementIsPresent(actionWindow)) {
			By allItems = By.xpath("//*[@id='myModal']/div/div/div[1]/h4/b[contains(text(),'Actions')]/following::table/tbody//a");
			List<WebElement> listOfItems = getListOfWebElementByElement(allItems);
			for (WebElement webElement : listOfItems) {
				String linkText = webElement.getText().toString().toLowerCase();
				if (linkText.contains(actionName.toLowerCase())) {
					webElement.click();
					By popupAfterclickingAttendanceLink = By
							.xpath("//*[@id=\"myModal\"]/div/div//button[@class='btn btn-primary' and text()='Close']");
					click(popupAfterclickingAttendanceLink, "button after successful attendance");
					sleep(500);
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
		// String currentDate = "July 09";
		String a = String.format(
				"//*[@id='ctl00_ContentPlaceHolder1_calAttendance']/tbody//tr/td[@class='aas_Present']//a[@title='%s']/following::td[text()='Present']",
				currentDate);
		By markedDate = By.xpath(a);
		try {
			status = getWebElement(markedDate).getText();
			ExtentLogger.pass("attendance marked successfully for " + currentDate, "yes");
		} catch (Exception e) {
			ExtentLogger.fail("attendance not marked for : " + currentDate +" as its being Sunday/Saturday or Public Holiday");
			throw new PropertyNotFoundException("Please mark attendance for valid day/date as " + currentDate
					+ " is either Sunday/Saturday or Holiday");
		}
		DriverManager.getDriver().close();
		// back to dashboard
		DriverManager.getDriver().switchTo().window(oldTab);

	}

	public String attendanceStatus() {
		return status;
	}

	public void markHrsForCurrentDay() {
		if (elementIsPresent(actionWindowCloseButton)) {
			click(actionWindowCloseButton, "close button in Action Modal");
		}
		click(timesheetLink, "link timesheet in dashboard");
		actionOnTimesheetTab();
	}

	public void actionOnTimesheetTab() {

		// considering that there is only one tab opened in that point.
		String oldTab = DriverManager.getDriver().getWindowHandle();
		// boolean foundElement = false;
		ArrayList<String> newTab = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());// return set of
																										// strings
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

		// String date = "12-Jul-2021";
		String date = Utilities.formatCurrentLocalDateForTimesheet();
		String b = String.format(
				"//*[@id='theadTimesheetModal']/tr[1]/th[2]/following::table[@id='datatableTimesheetModal']/tbody/tr[1]//td/input[@data-timesheetdate='%s']",
				date);
		By blankCells = By.xpath(b);// current day textbox
		getWebElement(blankCells).isDisplayed();
		List<WebElement> hrsTextBoxList = getListOfWebElementByElement(textBoxAll);
		for (WebElement dayTextBox : hrsTextBoxList) {
			String key = dayTextBox.getAttribute("data-timesheetdate");
			String value = dayTextBox.getAttribute("value");
			map.put(key, value);
		}

		String hrsForCurrentDate = map.get(date);
		if (hrsForCurrentDate.isBlank()) {
			try {
				selectFromDropdown(dropdownParentActivity, "2");
				type(blankCells, "9.00", "hrs in textbox");
				click(coforgeTimecardSaveButton, "button Save in timecard footer");
				click(coforgeTimecardCloseButtonAfterSubmission,
						"close button after entering hrs in textbox clicked successfully");
				Log.info("timesheet updated for :" + date);
				ExtentLogger.pass("timecard successfully filled for " + date);
			} catch (Exception e) {
				e.getMessage();
			}

		} else {
			if (checkBlankEntryForRemainngDays()) {
				Log.info("still hrs needs to be updated");
			} 
			else if (checkHoursEntryForRemainngDays()) {
				Log.info("everything is updated");
				finalTimeCardSubmit();
			} 
		}

	}

	public boolean checkBlankEntryForRemainngDays() {
		boolean status1 = false;
		for (String object : map.keySet()) {
			if (map.get(object).isEmpty()) {
				status1 = true;
				ExtentLogger.info("still hrs needs to be updated for : " + object);
				break;
			}
		}
		return status1;

	}
	public boolean checkHoursEntryForRemainngDays() {
		boolean status2 = false;
		for (String hrsInTextBox : map.keySet()) {
			String a = map.get(hrsInTextBox).toString().trim();
			if (a.equals("8.00")) {
				status2 = true;
			}
		}
		return status2;

	}

	public void finalTimeCardSubmit() {
		System.out.println("we can submit timesheet to approver");
		if (elementIsPresent(btnSucessTimeCard)) {
			click(btnSucessTimeCard, "timecard submitted successfully");
			click(btnAdminconfirmTimecard, "Confirming timecard submission");
			click(coforgeTimecardCloseButtonAfterSubmission, "close button after final confirmation");
		} else {
			ExtentLogger.info("timecard for this week has been already been submiited");
			Log.info("timecard for this week has been already been submiited");
		}

	}
}
