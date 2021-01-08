package pages;

import java.io.IOException;

import org.openqa.selenium.By;

import base.DriverManager;
import enums.EnumType;
import utility.ConfigFileReader;

public class LoginPage extends SelemiumAction {

	private By loginId = By.xpath("//*[@id=\"txtEmpCode\"]");
	private By loginPassword = By.xpath("//*[@id=\"txtPassword\"]");
	private By signButton = By.xpath("//*[@id=\"imgBtnOK\"]");
	private By loginButton = By.xpath("//*[@id=\"btnLogin\"]");

	public Dashboard enterCredentials(String value) throws IOException {
		DriverManager.getDriver().findElement(loginButton).click();
		EnumType type = EnumType.valueOfLabel(value);

		switch (type) {

		case TEAM_APPROVER:
			type(loginId, ConfigFileReader.getValue("approverusername"));
			type(loginPassword, ConfigFileReader.getValue("approverpassword"));
			click(signButton);
			break;
		case TEAM_LEADER:
			type(loginId, ConfigFileReader.getValue("leaderusername"));
			type(loginPassword, ConfigFileReader.getValue("leaderpassword"));
			click(signButton);
			break;
		default:
			System.out.println("no such access avaiable");
			break;
		}

		return new Dashboard();

	}

}
