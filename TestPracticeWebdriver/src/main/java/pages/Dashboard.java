
package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.DriverManager;

public class Dashboard extends SelemiumAction {

	private By attendanceLink = By.xpath("//a[contains(text(),'Attendance')and @class='thumb']");

	private By actionWindow = By.xpath("//*[@id=\"myModal\"]/div/div");

	private By markAttendanceLinkInActionsModal = By.xpath(
			"//div[@data-backdrop='static']//div[@class='modal-content']//b[text()=' Actions ']/following::a[text()='Click Here To Mark Attendance for Today']");

	private By selfDeclarationModal = By.xpath(
			"//div[@data-backdrop='static' and @id='SmQuestion']//div[@class='modal-content']//b[text()=' Self-declaration ']");

	private By submitButton = By.xpath("//*[@id=\"btnsubmit\"]");
	// private By closeButton = By.xpath("//*[@id=\"btnsubmit\"]");

	private By actonsModal = By
			.xpath("//div[@data-backdrop='static']//div[@class='modal-content']//b[text()=' Actions ']");

	private By actionWindowCloseButton = By.xpath("//*[@id=\"myModal\"]/div/div/div[3]/button");
	public boolean verifySelfDeclationWindowLoaded() {
		sleep(300);
		boolean status = false;
		try {
			if (getWebElement(selfDeclarationModal).isDisplayed()) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println("self-declaration modal not present");

		}
		return status;

	}

	public boolean accessAttendanceFromDashboard() {

		click(attendanceLink);
		sleep(2000);
		return true;

	}

	public boolean verifyActionWindowLoaded() {
		sleep(300);
		boolean status = false;
		try {
			if (getWebElement(actonsModal).isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("action modal not present");

		}
		return status;

	}

	public void actionInSelfModalWindow(String modalWindowName, String option) {
		String s = String.format(
				"//div[@class='modal-content']//b[contains(text(),'%s')]/following::table//input[@name='RequestType']",
				modalWindowName);
		List<WebElement> radioButtons = getListOfWebElementByString(s);
		for (WebElement options : radioButtons) {
			if (options.getAttribute("id").trim().equals(option)) {
				options.click();
				getWebElement(submitButton).click();
				acceptAlert("Accept");
				sleep(1000);
				break;
			} else {
				System.out.println("option not avaiable");
			}
		}

	}

	public void actionInActionModalWindow() {

		click(markAttendanceLinkInActionsModal);
	}

	public boolean verifyAttendanceLinkInDashboard(String actionName) {
//		if(radiobuttonwindowpresent) {
//			//click WFH raio button
//		}
		if (getWebElement(actionWindow).isDisplayed()) {
			String action = String.format(
					"//div[@class='modal-body']/table/tbody//td[contains(text(),'%s')]/following::tr[1]", actionName);
			By values = By.xpath(action);
			try {
				getWebElement(values).isDisplayed();
				int count = actionOnItemInActionsModal(values);
				if (count > 1) {
					actionOnNewTab(values);
				}
				
			} catch (Exception e) {
				e.getMessage();
			} finally {
				
				click(actionWindowCloseButton);
			}
			/*
			 * By values =
			 * By.xpath("//*[@id=\"myModal\"]//div[@class=\"modal-body\"]/table//td");
			 * List<WebElement> listOfActions = getListOfWebElementByElement(values);
			 * List<String> listOfActionsPresentText = new ArrayList<>(); for (int i = 0; i
			 * < listOfActions.size(); i++) { // loading text of each element in to array
			 * all_elements_text
			 * listOfActionsPresentText.add(listOfActions.get(i).getText()); } for (String
			 * string : listOfActionsPresentText) { if (string.contains(actionName)) {
			 * System.out.println(actionName + "found in action window"); break; } }
			 */
			// close button of actionwindow

		}

		return getWebElement(attendanceLink).isDisplayed();

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

}
