package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;

public class ExcelUtility {
    private String filePath;
    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;

    // Constructor to initialize the file path
    public ExcelUtility(String filePath) {
        this.filePath = filePath;
        System.out.println("Excel File Path: " + filePath); // Debugging
    }

    // 1️⃣ Get the total number of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        
        int rowCount = sheet.getLastRowNum() + 1;  // Get row count
        workbook.close();
        fi.close();
        
        return rowCount;
    }

    // 2️⃣ Get the total number of columns in a specific row
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        
        row = sheet.getRow(rowNum);
        int cellCount = (row != null) ? row.getLastCellNum() : 0;
        
        workbook.close();
        fi.close();
        
        return cellCount;
    }

    // 3️⃣ Read data from a specific cell
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        
        row = sheet.getRow(rowNum);
        if (row == null) return "";
        
        cell = row.getCell(colNum);
        if (cell == null) return "";

        String cellData;
        switch (cell.getCellType()) {
            case STRING: cellData = cell.getStringCellValue(); break;
            case NUMERIC: cellData = String.valueOf(cell.getNumericCellValue()); break;
            case BOOLEAN: cellData = String.valueOf(cell.getBooleanCellValue()); break;
            default: cellData = "";
        }

        workbook.close();
        fi.close();
        return cellData;
    }

    // 4️⃣ Write data to a specific cell
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        fi.close();

        row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(data);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    // 5️⃣ Fill a cell with green color
    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        fi.close();

        row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        // Set green background
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    // 6️⃣ Fill a cell with red color
    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
        fi = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        fi.close();

        row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        // Set red background
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(filePath);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }
}
