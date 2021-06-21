package pages;

import org.openqa.selenium.By;

import reports.ExtentLogger;

public class LoginPage extends SelemiumAction {
	private By loginId = By.xpath("//*[@id=\"txtEmpCode\"]");
	private By loginPassword = By.xpath("//*[@id=\"txtPassword\"]");
	private By signButton = By.xpath("//*[@id=\"imgBtnOK\"]");
	private By loginButton = By.xpath("//*[@id=\"btnLogin\"]");

	/*
	 * public Dashboard enterCredentials(EnumType userType) throws IOException {
	 * //DriverManager.getDriver().findElement(loginButton).click(); //EnumType type
	 * = EnumType.valueOfLabel(userType); click(loginButton); String type
	 * =userType.label;
	 * 
	 * switch (type) {
	 * 
	 * case "admin": type(loginId, ConfigFileReader.getValue("approverusername"));
	 * type(loginPassword, ConfigFileReader.getValue("approverpassword"));
	 * click(signButton); break; case "leader": type(loginId,
	 * ConfigFileReader.getValue("leaderusername")); type(loginPassword,
	 * ConfigFileReader.getValue("leaderpassword")); click(signButton); break;
	 * default: System.out.println("no such access avaiable"); break; }
	 * 
	 * return new Dashboard();
	 * 
	 * }
	 */

	public Dashboard usingCorrectCredentials(String username, String password) {
		click(loginButton,"login button");
		type(loginId,username,"username");
		type(loginPassword,utility.Utilities.getDecodedString(password),"password");
		click(signButton,"signin button");
		ExtentLogger.pass("logged in successfull");
		return new Dashboard();
	}

}
