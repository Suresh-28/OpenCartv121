package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
      @DataProvider(name = "LoginData")
	   public String[][] getData() throws IOException {
	        String path = ".\\testData\\Opencart.xlsx"; // Path to Excel file

	        ExcelUtility xlutil = new ExcelUtility(path); // Creating an object for ExcelUtility

	        int totalRows = xlutil.getRowCount("Sheet1");
	        int totalCols = xlutil.getCellCount("Sheet1", 1);

	        // Creating a two-dimensional array to store data
	        String logindata[][] = new String[totalRows][totalCols];

	        // Reading data from Excel and storing it in the array
	        for (int i = 1; i <= totalRows; i++) { // Starting from row 1 (skipping header)
	            for (int j = 0; j < totalCols; j++) {
	                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // Storing data
	            }
	        }
	        return logindata; // Returning two-dimensional array
	    }
	}
	

