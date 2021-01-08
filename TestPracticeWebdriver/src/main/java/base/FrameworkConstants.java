package base;

public final class FrameworkConstants {
	
	private static final String chromeDriverPath = System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe";
	//this should be in config.properties
	
	private static final String configFilerPath = System.getProperty("user.dir")+"/src/test/resources/config.properties";


	private static String extentConfigPath = System.getProperty("user.dir")+"/src/test/resources/extentreport.xml";


	public static String getChromedriverpath() {
		return chromeDriverPath;
	}



	public static String getConfigfilerpath() {
		return configFilerPath;
	}



	public static String getExtentConfigPath() {
		return extentConfigPath;
	}

	
	

}
