package testcases;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import dataProvider.DataProviderClass;
import reports.ExtentLogger;

public class LogInTest extends BaseTest {

	/*
	 * @Test public void verifyAttendanceWithValidCredentials() throws IOException {
	 * System.out.println("The thread ID for Chrome is " +
	 * Thread.currentThread().getId());
	 * login.enterCredentials(EnumType.TEAM_APPROVER);
	 * assertTrue(dashboard.verifyAttendanceLinkInDashboard());
	 * 
	 * }
	 */


	@Test(dataProvider = "getUserThruExcel", dataProviderClass = DataProviderClass.class)
	public void verifyAttendanceForCurrentDate(Map<String, String> map) throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		ExtentLogger.info("TC started");
		loginIntoIengagePortal.usingCorrectCredentials(map.get("username"), map.get("password"))
				.verifingAttendanceLinkInDashboard("Attendance");
		assertTrue(dashboard.verifyAttendanceMarkedForDay());
	}

}
