package testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import enums.EnumType;

public class LogInTest extends BaseTest {

	@Test
	public void verifyAttendanceWithValidCredentials() throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		login.enterCredentials(EnumType.TEAM_APPROVER);
		assertTrue(dashboard.verifyAttendanceLinkInDashboard());

	}
	
	@Test
	public void verifyAttendanceWithInValidCredentials() throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		login.enterCredentials(EnumType.TEAM_LEADER);
		assertTrue(dashboard.verifyAttendanceLinkInDashboard());
	}
	
}
