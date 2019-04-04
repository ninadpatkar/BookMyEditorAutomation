package Utilities;

import java.io.File;

import org.testng.annotations.DataProvider;

public class BMETestData {
	
	@DataProvider(name="inputsBME")
	public Object[][] getData() throws Exception{
		
		ExcelUtility.setExcelFile(Constants.File_path, Constants.Sheet_Name);
		
		Object[][] testData = ExcelUtility.getTestData("TestData");
		return testData;
	}
	
}
