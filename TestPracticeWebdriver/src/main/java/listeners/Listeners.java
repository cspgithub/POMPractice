
package listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentLogger;
import reports.ExtentReport;

//  
//  Listener class which is implementing ITestListener and hence we can use this
//  to dynamically create reports, write logs.

public class Listeners implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReports();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getMethodName(), "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// ExtentLogger.fail(result.getMethod().getMethodName() + "is failed");

		ExtentLogger.fail(result.getThrowable().getMessage());
		ExtentLogger.fail(result.getMethod().getMethodName(), "yes");

		// attach screenshot
	}

}
