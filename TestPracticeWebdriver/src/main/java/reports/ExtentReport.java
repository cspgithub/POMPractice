
package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReport {
	

	private ExtentReport() {
	}

	private static ExtentReports extent;

	public static void initReports() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("index.html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
			//spark.config().setDocumentTitle("Automation report");
			final File CONF = new File(".//src/test/resources/extentconfig.xml");
			spark.loadXMLConfig(CONF);
			extent.attachReporter(spark);
		}

	}

	public static void flushReports() {

		extent.flush();
		try {
			Desktop.getDesktop().browse(new File("index.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createTest(String testname) {
		ExtentManager.setExtentTest(extent.createTest(testname));
	}

	

}
