package testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataProvider.DataProviderClass;
import dataProvider.UserType;
import enums.EnumType;

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
	
	@Test(dataProvider = "getUserThruExcel",dataProviderClass = DataProviderClass.class)
	public void veriWithInValidCredentials(Map<String,String>map) throws IOException {
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		login.enterCredentials(map.get("username"),map.get("password"));
		assertTrue(dashboard.verifyAttendanceLinkInDashboard());
//		System.out.println(userType.getUsername());
//		System.out.println(userType.getPassword());
	}
	
}
