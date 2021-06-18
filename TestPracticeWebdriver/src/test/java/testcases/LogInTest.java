package testcases;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import dataProvider.DataProviderClass;

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
	
//	@Test(dataProvider = "getUserThruExcel",dataProviderClass = DataProviderClass.class)
//	public void verifyAttendanceLinkAttendance(Map<String,String>map) throws IOException {
//		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
//		//login.enterCredentials(null).verifyAttendanceLinkInDashboard(null);
//		login.enterCredentials(map.get("username"),map.get("password"));
//		assertTrue(dashboard.verifyAttendanceLinkInDashboard("Attendance"));
////		System.out.println(userType.getUsername());
////		System.out.println(userType.getPassword());
//	}
	
	@Test(dataProvider = "getUserThruExcel",dataProviderClass = DataProviderClass.class)
	public void verifyAttendance(Map<String,String>map) throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		//login.enterCredentials(null).verifyAttendanceLinkInDashboard(null);
		login.enterCredentials(map.get("username"),map.get("password"));
		dashboard.verifyAttendanceLinkInDashboard("Attendance");
		assertTrue(dashboard.verifyAttendanceMarkedForDay());
	}
	
}
