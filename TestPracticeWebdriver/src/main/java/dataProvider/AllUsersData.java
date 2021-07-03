package dataProvider;

import java.util.List;
import java.util.Map;

public class AllUsersData {
	
	private Map<String, List<TestData>> allTestCaseDataMap;

	public Map<String, List<TestData>> getAllTestCaseDataMap() {
		return allTestCaseDataMap;
	}

	public void setAllTestCaseDataMap(Map<String, List<TestData>> allTestCaseDataMap) {
		this.allTestCaseDataMap = allTestCaseDataMap;
	}
	
	// key : admin
	//value: username: kkk,password:ioioioio
	

}
