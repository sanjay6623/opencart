package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		
		String path=".\\testData\\opencarttest.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object of ExcelUtility class
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];// created for two dimension array which can store data 
		
		for(int r=1;r<=totalrows;r++) { //1  r= row, c=cols
			
			for(int c=0;c<totalcols;c++) {
				
				logindata[r-1][c]=xlutil.getCellData("Sheet1", r, c);
				
			}
		}
		return logindata;
		
		
	}
	
	

}
