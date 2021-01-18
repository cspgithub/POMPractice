package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider
	public UserType[] getUser() {
		return new UserType[] { new UserType("77378", "Coforge@2021!")

		};

	}

	@DataProvider
	public Object[] getUserThruExcel() throws IOException {
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet =workbook.getSheet("testing");
		int rownum = sheet.getLastRowNum();
		int columnnum = sheet.getRow(0).getLastCellNum();
		
		Object[] data = new Object[rownum];
		Map<String, String>map;
		for(int i=1;i<=rownum;i++) {
			map = new HashMap<>();
			for (int j = 0; j <columnnum; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				map.put(key, value);
				data[i-1]=map;
			}
			
			
		}
		
		return data;

	}
}
