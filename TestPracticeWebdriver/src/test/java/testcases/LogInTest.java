package testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

public class LogInTest extends BaseTest {

	@Test
	public void verifyAttendanceWithInvalidCredentials() throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		assertTrue(login.enterCredentials("leader").verifySelfDeclationWindowLoaded());
//		if (dashboard.verifySelfDeclationWindowLoaded()) {
//			dashboard.actionInSelfModalWindow(" Self-declaration ", "RequestTypeR1");
//		} else if (dashboard.verifyActionWindowLoaded()) {
//			dashboard.actionInActionModalWindow();
//		}
//		assertTrue(dashboard.verifyAttendanceLinkInDashboard());

	}

}
