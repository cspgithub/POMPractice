package testcases;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Test;

import dataProvider.AdminUserData;
import dataProvider.DataProviderClass;
import reports.ExtentLogger;

public class LogInTest2 extends BaseTest {

	/*
	 * @Test public void verifyAttendanceWithValidCredentials() throws IOException {
	 * System.out.println("The thread ID for Chrome is " +
	 * Thread.currentThread().getId());
	 * login.enterCredentials(EnumType.TEAM_APPROVER);
	 * assertTrue(dashboard.verifyAttendanceLinkInDashboard());
	 * 
	 * }
	 */

	@Test(dataProvider = "userDataYml", dataProviderClass = DataProviderClass.class)
	public void admin(Map<Object, Object> map) {
	
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		ExtentLogger.info("TC started");
		loginIntoIengagePortal.usingCorrectCredentialsYaml(map.get("name").toString(),map.get("password").toString()).dashboardloaded();
		dashboard.verifingAttendanceLinkInDashboard("Attendance");
		assertTrue(dashboard.verifyAttendanceMarkedForDay());
	}
}
