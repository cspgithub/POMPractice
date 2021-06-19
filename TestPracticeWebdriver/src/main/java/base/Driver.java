package base;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import enums.EnumType;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigFileReader;

public class Driver {

	private static WebDriver driver;
	private static String browsername;
	private static EnumType enumType;

	protected static String getBrowser() throws IOException {
		browsername = ConfigFileReader.getValue("browser");
		return browsername;
	}

	public static void setUpDocker() throws IOException, InterruptedException {
		if (ConfigFileReader.getValue("executionmode").equalsIgnoreCase("remote")) {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("cmd /c start dockerUp.bat");
			Thread.sleep(1000);

		}
	}

	public static void closeDocker() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start dockerClose.bat");
		runtime.exec("taskkill /f /im cmd.exe");
	}

	public static void initateDriver() {
		if (Objects.isNull(DriverManager.getDriver())) {
			try {
				//setUpDocker();
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

			createRemoteDriver(ConfigFileReader.getValue("browser"));

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
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", FrameworkConstants.getChromedriverpath());
			driver = new ChromeDriver();
			break;

		case "firefox":
			//System.setProperty("webdriver.firefox.driver", FrameworkConstants.getChromedriverpath());
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Invalid browser passed :" + browser);
			break;
		}
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().get(ConfigFileReader.getValue("url"));

	}

	private static void createRemoteDriver(String browser) throws IOException {
		// docker run -d -p 4444:4444 -v /dev/shm:/dev/shm
		// selenium/standalone-chrome:latest
		System.out.println("Browser Selected on Docker :" + browser);
		DesiredCapabilities capability = null;
		
		switch (browser) {
		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			break;

		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			FirefoxOptions options = new FirefoxOptions();
		    options.setHeadless(false);
		    System.out.println(options.getBrowserName());
		    capability.merge(options);
			break;

		default:
			System.out.println("Invalid browser passed :" + browser);
			break;
		}
		capability.setPlatform(Platform.ANY);
		driver = new RemoteWebDriver(new URL(ConfigFileReader.getValue("remoteurl")), capability);
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().deleteAllCookies();
		DriverManager.getDriver().get(ConfigFileReader.getValue("url"));

	}

	public static void quitDriver()  {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().close();
			DriverManager.getDriver().quit();
			DriverManager.unload();
			
		}
	}

}