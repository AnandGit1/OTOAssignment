package pages;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import src.test.java.testbase.TestBase;
import utils.ExcelReader;
import utils.LoggerHelper;

public class ConfigValues extends TestBase
{

	private static Logger log = LoggerHelper.getLogger(ConfigValues.class);

	public ConfigValues(WebDriver driver)
	{		
		super();		
		Setup();
		this.driver = driver;
	}

	String environment=prop.getProperty("Environment");
	public static ResultSet Status;
	public static String InvoiceNo;	
	public static String TestCaseName=null;
	public static String ExtentReportsDir=System.getProperty("user.dir")+"\\extent-reports\\";
	
	public static String Applicationurl;
	public static String db_connect_string;
	public static String db;
	public static String db_userid;
	public static String db_password;	
	public static String testdatapath;
	
	public void Setup()
	{	
		switch(prop.getProperty("Environment"))
		{
		case "UAT":
			Applicationurl=prop.getProperty("UAT_Applicationurl");
			System.out.println("Opening Applicationurl ====>"+Applicationurl);
			/*
			 * db_connect_string=prop.getProperty("UAT_db_connect_string");
			 * db=prop.getProperty("UAT_db_name");
			 * db_userid=prop.getProperty("UAT_db_userid");
			 * db_password=prop.getProperty("UAT_db_password");
			 * testdatapath=prop.getProperty("UAT_testdata");
			 */
			break;
		case "Dev":
			Applicationurl=prop.getProperty("Dev_Applicationurl");
			/*
			 * db_connect_string=prop.getProperty("Dev_db_connect_string");
			 * db=prop.getProperty("Dev_db_name");
			 * db_userid=prop.getProperty("Dev_db_userid");
			 * db_password=prop.getProperty("Dev_db_password");
			 * testdatapath=prop.getProperty("Dev_testdata");
			 */			
			break;
		}
	}

	//Details Section	
	public static String UserName=null;
	public static String Password=null;
	public static String InvoiceAction=null;
	public static String Company=null;
	public static String Category=null;
	public static String SubCategory=null;
	public static String InvoiceType=null;
	public static String APOwner=null;
	public static String Requestor=null;
	public static String DocumentNumber=null;
	public static String Notes=null;

	//Supplier Details
	public static String Supplier=null;


	public static String InvoiceDate=null;
	public static String Amount=null;
	public static String SupplierInvoiceNumber=null;
	
	public static String distributionList=null;
	
	public static Float codingAmount;
	public static Float codingFrieght;
	public static Float codingTotal;
	

	public static void gettestdata(ExtentTest test,String testCaseName,String Sheetname)
	{		
		log.info("Reading testdata");

		ExcelReader Data = new ExcelReader();
		int row=Data.findRow(Sheetname, testCaseName)+1;	

		UserName=Data.getCellData(Sheetname, "APOwner", row);
		Password=ExcelReader.getExceltestdata("Users",Data.getCellData(Sheetname, "APOwner", row),"Password");
		InvoiceAction=Data.getCellData(Sheetname, "InvoiceAction", row);
		Company=Data.getCellData(Sheetname, "Company", row);
		Category=Data.getCellData(Sheetname, "Category", row);
		SubCategory=Data.getCellData(Sheetname, "SubCategory", row);
		InvoiceType=Data.getCellData(Sheetname, "InvoiceType", row);
		APOwner=Data.getCellData(Sheetname, "APOwner", row);
		Requestor=Data.getCellData(Sheetname, "Requestor", row);
		DocumentNumber=Data.getCellData(Sheetname, "DocumentNumber", row);
		Notes=Data.getCellData(Sheetname, "Notes", row);

		Supplier=Data.getCellData(Sheetname, "Supplier", row);
		InvoiceDate=Data.getCellData(Sheetname, "InvoiceDate", row);
		Amount=Data.getCellData(Sheetname, "Amount", row);
		SupplierInvoiceNumber=Data.getCellData(Sheetname, "SupplierInvoiceNumber", row);	
		
		codingAmount=0.00f;
		codingFrieght=0.00f;
		codingTotal=0.00f;
		
	}	
	
	public static void updateCredentials(String username,String password)
	{
		UserName=username;
		Password=password;		
	}
	
	public static String getConfiValue(String key)
	{		
		return prop.getProperty(key);
		
	}

}
