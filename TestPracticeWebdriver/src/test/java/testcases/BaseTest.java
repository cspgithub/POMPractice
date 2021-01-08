package testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.Driver;
import base.Pageinitialize;

public class BaseTest implements Pageinitialize {

	static String browser;
	protected BaseTest() {
		// no one can make object of this class
	}

	@BeforeMethod
	protected void Start() throws IOException {
		Driver.initateDriver();
	}

	@AfterMethod
	protected void Close() {
		Driver.quitDriver();

	}

}
