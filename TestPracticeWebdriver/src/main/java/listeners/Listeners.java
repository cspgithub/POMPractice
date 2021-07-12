
package listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentLogger;
import reports.ExtentReport;
import utility.Log;

//  
//  Listener class which is implementing ITestListener and hence we can use this
//  to dynamically create reports, write logs.

public class Listeners implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReports();
		} catch (IOException e) {
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
		ExtentLogger.info("Test Case : " + result.getMethod().getMethodName() + " execution Started");
		Log.info("Test Case : " + result.getMethod().getMethodName() + " execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getMethodName(), "yes");
			ExtentLogger.info("Test Case : " + result.getMethod().getMethodName() + " execution Finished");
			Log.info("Test Case : " + result.getMethod().getMethodName() + " execution Finished");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		//ExtentLogger.fail(result.getThrowable().getMessage());
		ExtentLogger.fail(result.getMethod().getMethodName(), "yes");
		ExtentLogger.info("Test Case : " + result.getMethod().getMethodName() + " execution Finished");
		Log.info("Test Case : " + result.getMethod().getMethodName() + " execution Finished");
	
	}

}
