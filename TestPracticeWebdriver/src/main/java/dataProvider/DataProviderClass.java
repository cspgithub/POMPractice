package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import base.FrameworkConstants;

public class DataProviderClass {
	String excelPath = FrameworkConstants.getExcelUserCredentialsPath();
	String ymlPath = FrameworkConstants.getYmlfilepath();

	@DataProvider(name = "userData")
	public Object[] userFormData(ITestNGMethod testContext) throws Exception {
		if (testContext.getMethodName().equals("verifyAttendanceForCurrentDateInvalidUser")) {
			return getUserThruExcel(excelPath, "invalidScenario");
		} else {
			return getUserThruExcel(excelPath, "validScenario");

		}

	}

	@DataProvider(name = "userDataYml")
	public Object[][] getDataFromYamlFile(Method method) {

		Yaml yaml = new Yaml();
		AllUsersData allUsersData = null;
		try {

			allUsersData = yaml.loadAs(new FileReader(new File(ymlPath)), AllUsersData.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String testCaseName = method.getName();

		List<TestData> testDataSets = allUsersData.getAllTestCaseDataMap().get(testCaseName);

		Object[][] data = new Object[testDataSets.size()][1]; // on runtime, rows will be decided, column will always be
																// 1 in all the cases

		// on runtime Object[n][1] array will be populated based on list size
		for (int i = 0; i < testDataSets.size(); i++) {
			data[i][0] = testDataSets.get(i);
		}

		return data;

	}

	/*
	 * private static Iterator<Object[]> getUserDetails(final Object[][] objArr) {
	 * List<AdminUserData> testList = new ArrayList<>(); ArrayList<String> list =
	 * new ArrayList<>(); for (Object[] arr : objArr) { // AdminUserData user = new
	 * AdminUserData(); for (int i = 0; i < arr.length; i++) {
	 * Collections.addAll(list, arr[i].toString()); } } HashMap<String, String>
	 * languageMap = convertArrayListToHashMap(list); for (Entry<String, String>
	 * entry : languageMap.entrySet()) { AdminUserData user = new AdminUserData();
	 * user.setName(entry.getKey()); user.setPassword(entry.getKey());
	 * testList.add(user); }
	 * 
	 * 
	 * Collection<Object[]> dp = new ArrayList<Object[]>(); for (AdminUserData
	 * userDetails : testList) { dp.add(new Object[] { userDetails }); } return
	 * dp.iterator();
	 * 
	 * }
	 */

	/*
	 * private static HashMap<String, String>
	 * convertArrayListToHashMap(ArrayList<String> arrayList) {
	 * 
	 * HashMap<String, String> hashMap = new HashMap<>();
	 * 
	 * for (String str : arrayList) {
	 * 
	 * hashMap.put(str, "1");
	 * 
	 * }
	 * 
	 * return hashMap; }
	 */

	public Object[] getUserThruExcel(String excelpath, String sheetName) throws IOException {
		FileInputStream fs = new FileInputStream(excelpath);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rownum = sheet.getLastRowNum();
		int columnnum = sheet.getRow(0).getLastCellNum();

		Object[] excelData = new Object[rownum];
		Map<String, String> map;
		for (int i = 1; i <= rownum; i++) {
			map = new HashMap<>();
			for (int j = 0; j < columnnum; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				map.put(key, value);
				excelData[i - 1] = map;
			}

		}

		return excelData;

	}
}
