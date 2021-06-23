package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import base.FrameworkConstants;

public class DataProviderClass {
	String excelPath = FrameworkConstants.getExcelUserCredentialsPath();

	@DataProvider(name = "userData")
	public Object[] userFormData(ITestNGMethod testContext) throws Exception {
		if (testContext.getMethodName().equals("verifyAttendanceForCurrentDateInvalidUser")) {
			return getUserThruExcel(excelPath, "invalidScenario");
		} else {
			return getUserThruExcel(excelPath, "validScenario");

		}

	}

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
