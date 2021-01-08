package base;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import enums.EnumType;
import utility.ConfigFileReader;

public class Driver {

	private static WebDriver driver;
	private static String browsername;
	private static EnumType enumType;

	protected static String getBrowser() throws IOException {
		browsername = ConfigFileReader.getValue("browser");
		return browsername;
	}

	public static void initateDriver() {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				createDriver();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void createDriver() throws IOException {
		enumType = EnumType.valueOfLabel(ConfigFileReader.getValue("executionmode"));
		switch (enumType) {
		case LOCAL_ENV:
			createLocalDriver(ConfigFileReader.getValue("browser"));
			break;
		case REMOTE_ENV:
			System.out.println("code not imlemented yet");
			break;
		default:
			System.out.println("please provide correct environment");
			break;
		}

	}

	private static void createLocalDriver(String browser) throws IOException {
		System.out.println("Browser Selected :" + browser);
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverpath());
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverpath());
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Invalid browser passed :" + browser);
			break;
		}
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().get(ConfigFileReader.getValue("url"));

	}

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().close();
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}