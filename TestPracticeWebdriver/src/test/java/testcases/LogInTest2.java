package testcases;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

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
	
	
	@Test
	public void verifyAttendanceFor(){
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		ExtentLogger.info("TC started");
		loginIntoIengagePortal.usingCorrectCredentialsYaml("admin","adminpassword")
				.verifingAttendanceLinkInDashboard("Attendance");
		assertTrue(dashboard.verifyAttendanceMarkedForDay());
	}
}
