package testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import base.Driver;
import base.Pageinitialize;
import enums.EnumType;

public class BaseTest implements Pageinitialize {

	
	protected BaseTest() {
		// no one can make object of this class
	}

	@BeforeSuite
	public void start() throws IOException, InterruptedException {
		Driver.setUpDocker();
		
	}

	@Override @BeforeMethod
	public void startDriver() {
		System.out.println("calling interface function");
		Driver.initateDriver();
		
		
	}
	@AfterMethod
	protected void Close() {
		Driver.quitDriver();
	

	}
	
	@AfterSuite
	public void CloseD() throws IOException {
		Driver.closeDocker();
	}

	
	
	

	
}
