
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Dashboard extends SelemiumAction {

	
	private By attendanceLink = By.xpath("//a[contains(text(),'Attendance')and @class='thumb']");

	//private By timeSheetLink = By.xpath("//a[contains(text(),'TimeSheet')and @class='thumb']");

	private By markAttendanceLinkInActionsModal = By.xpath(
			"//div[@data-backdrop='static']//div[@class='modal-content']//b[text()=' Actions ']/following::a[text()='Click Here To Mark Attendance for Today']");

	private By selfDeclarationModal = By.xpath(
			"//div[@data-backdrop='static' and @id='SmQuestion']//div[@class='modal-content']//b[text()=' Self-declaration ']");

	private By submitButton = By.xpath("//*[@id=\"btnsubmit\"]");
	// private By closeButton = By.xpath("//*[@id=\"btnsubmit\"]");

	private By actonsModal = By
			.xpath("//div[@data-backdrop='static']//div[@class='modal-content']//b[text()=' Actions ']");

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

	public boolean verifyAttendanceLinkInDashboard() {
		return getWebElement(attendanceLink).isDisplayed();
		//return DriverManager.getDriver().getTitle().trim();
	}
	
}
