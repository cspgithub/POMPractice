package testcases;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import dataProvider.DataProviderClass;
import reports.ExtentLogger;
import utility.Log;

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

//	
//	@Test(dataProvider = "userData", dataProviderClass = DataProviderClass.class)
//	public void verifyAttendanceForCurrentDateInvalidUser(Map<String, String> map) throws IOException {
//		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
//		ExtentLogger.info("TC started");
//		loginIntoIengagePortal.usingCredentials(map.get("username"), map.get("password"))
//				.verifingAttendanceLinkInDashboard("Attendance");
//		assertTrue(dashboard.verifyAttendanceMarkedForDay());
//	}
	
	@Test(dataProvider = "userData", dataProviderClass = DataProviderClass.class)
	public void verifyAttendanceForCurrentDateValidUser(Map<String, String> map) throws IOException {
		//System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		Log.info("The thread ID for Chrome is " + Thread.currentThread().getId());
		loginIntoIengagePortal.usingCredentials(map.get("username"), map.get("password")).verifingAttendanceLinkInDashboard("Attendance");
		dashboard.verifyAttendanceMarkedForDay();
		assertTrue(dashboard.attendanceStatus().contains("Present"));
		dashboard.markHrsForCurrentDay();
	}

}
