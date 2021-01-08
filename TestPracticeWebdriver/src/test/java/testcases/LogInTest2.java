package testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

public class LogInTest2 extends BaseTest {

	@Test
	public void verifyAttendanceWithValidCredentials() throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		login.enterCredentials("admin");
		assertTrue(dashboard.verifyAttendanceLinkInDashboard());
		

	}

}
