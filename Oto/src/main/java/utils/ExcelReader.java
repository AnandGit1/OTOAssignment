package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import pages.ConfigValues;


public class ExcelReader {

	String excelPath;
	FileInputStream fileInputStream;
	XSSFWorkbook workbook; 
	XSSFSheet sheet;

	public ExcelReader()
	{
		try
		{
			excelPath =System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\OTO_TestData.xlsx";
			fileInputStream = new FileInputStream(new File(excelPath));

			// Create Workbook instance holding .xls file
			workbook = new XSSFWorkbook(fileInputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) 
	{
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum-1);
			XSSFCell cell = row.getCell(col_Num);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "BLANK";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public int findRow(String sheetName, String cellContent) 
	{
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) 
			{
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
			}
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) 
						{
							return row.getRowNum();  
						}
					}
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int findExcelRow(String sheetName,String cellContent,String colName)
	{

		try {

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1) 
			{
				return 0;
			} 
			else 
			{
				sheet = workbook.getSheetAt(index);
			}		

			int columnNo = -1;
			Row row = sheet.getRow(0);

			for (Cell cell : row) {
				if (cell.getStringCellValue().equals(colName)){
					columnNo = cell.getColumnIndex();
					break;
				}
			}

			if (columnNo>=0){
				for (Row row1 : sheet) {
					Cell cell = row1.getCell(columnNo);

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) 
						{
							return row1.getRowNum();  
						}
					}					
				}
			}
			else
			{
				System.out.println("could not find column " + colName + " in not found");
			}	
		}
		catch(Exception e)
		{

		}

		return 0;

	}

	public static String getExceltestdata(String Sheetname,String testCaseName,String fieldName)
	{
		ExcelReader Data = new ExcelReader();
		try {
			int row=Data.findRow(Sheetname, testCaseName)+1;		
			return Data.getCellData(Sheetname, fieldName, row).trim();
		}
		catch(Exception e)
		{

		}
		return null;
	}
	
	public static String getExceltestdataonColumn(String Sheetname,String testCaseName,String fieldName)
	{
		ExcelReader Data = new ExcelReader();
		try {
			int row=Data.findExcelRow(Sheetname, testCaseName,"UserName")+1;		
			return Data.getCellData(Sheetname, fieldName, row).trim();
		}
		catch(Exception e)
		{

		}
		return null;
	}
	
	public String getColName(String sheetName,int ColNum) 
	{
		try {
			int index = workbook.getSheetIndex(sheetName);
			XSSFRow row = sheet.getRow(0);
			//XSSFCell cell = row.getCell(ColNum);
			return row.getCell(ColNum).getStringCellValue();
		}
		
		catch(Exception e)
		{

		}
		return null;
	}


	/*@Test
	public void test1()
	{
		System.out.println(findExcelRow("Users","Full Name","Ca Test57 - X17057"));
		System.out.println(findExcelRow("Users","UserName","X17053"));
	}
*/
}
