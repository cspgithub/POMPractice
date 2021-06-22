
package reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.DriverManager;
import utility.ConfigFileReader;

public class ExtentLogger {

	private ExtentLogger() {
		// private to avoid initialization
	}

	public static void pass(String message) {
		ExtentManager.getExtTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	public static void fail(String message) {

		ExtentManager.getExtTest().fail(message);
	}

	public static void info(String message) {

		ExtentManager.getExtTest().info(message);
	}

	public static void skip(String message) {

		ExtentManager.getExtTest().skip(message);
	}

	// overloaded
	public static void pass(String message, String isScreenshotreuired) throws Exception {
		if (ConfigFileReader.getValue("PassedStepsScreenshots").equalsIgnoreCase("yes")
				&& ConfigFileReader.getValue("screenshotsrequired").equalsIgnoreCase("yes")) {

			ExtentManager.getExtTest().pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());

		} else {
			skip(message);
		}

	}

	public static void fail(String message, String isScreenshotreuired) {

		if (ConfigFileReader.getValue("failedstepsscreenshots").equalsIgnoreCase("yes")
				&& ConfigFileReader.getValue("screenshotsrequired").equalsIgnoreCase("yes")) {

			ExtentManager.getExtTest().fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());

		} else {
			fail(message);
		}
	}

	public static void skip(String message, String isScreenshotreuired) throws Exception {

		if (ConfigFileReader.getValue("SkippedStepsScreenshots").equalsIgnoreCase("yes")
				&& ConfigFileReader.getValue("screenshotsrequired").equalsIgnoreCase("yes")) {

			ExtentManager.getExtTest().skip(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());

		} else {
			skip(message);
		}
	}

	public static String getBase64Image() {

		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

	}

}
