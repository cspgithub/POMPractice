package base;

public final class FrameworkConstants {

	private static final String chromeDriverPath = System.getProperty("user.dir")
			+ "/src/test/resources/chromedriver.exe";
	// this should be in config.properties

	private static final String configFilerPath = System.getProperty("user.dir")
			+ "/src/test/resources/config.properties";

	private static final String ymlFilePath = System.getProperty("user.dir") + "/src/test/resources/User.yml";

	private static String extentConfigPath = System.getProperty("user.dir") + "/src/test/resources/extentreport.xml";

	private static String excelUserCredentialsPath = System.getProperty("user.dir")
			+ "/src/test/resources/testdata.xlsx";

	public static String getChromedriverpath() {
		return chromeDriverPath;
	}

	public static String getConfigfilerpath() {
		return configFilerPath;
	}

	public static String getExtentConfigPath() {
		return extentConfigPath;
	}

	public static String getYmlfilepath() {
		return ymlFilePath;
	}

	public static String getExcelUserCredentialsPath() {
		return excelUserCredentialsPath;
	}

}
