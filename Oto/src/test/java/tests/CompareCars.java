package tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.ConfigValues;
import pages.compareCarsPage;
import pages.selectCarsPage;
import testbase.TestBase;
import utils.ExcelReader;
import utils.ExtentReportsHelper;
import utils.LoggerHelper;

public class CompareCars extends TestBase{

	private Logger log = LoggerHelper.getLogger(CompareCars.class);

	WebDriver driver;
	ConfigValues configvalues;
	selectCarsPage selectcarspage;
	compareCarsPage comparecarspage;
	ExcelReader testData;

	CompareCars()
	{
		super();

	}

	ExtentHtmlReporter htmlReports;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void Testsetup()
	{
		String filename=ExtentReportsHelper.getReportspath(this.getClass().getSimpleName());
		htmlReports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Regression Testing_"+utils.DateTimeHelper.getCurrentDate("datetime"));
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setTestViewChartLocation(ChartLocation.TOP);		
		System.out.println("Reports file is loacated in the path --> "+filename);
		configvalues=new ConfigValues(driver);
		testData = new ExcelReader();
	}

	@BeforeMethod
	public void setup() throws Exception
	{
		driver=initialization();	
		selectcarspage=new selectCarsPage(driver);
		comparecarspage=new compareCarsPage(driver);
	}

	@AfterMethod
	public void teardown(ITestResult testResults) throws Exception
	{
		ExtentReportsHelper.UpdateResult(driver, testResults, test);
		Thread.sleep(5000);
		driver.quit();
		extent.flush();
	}

	@AfterTest
	public void endReport()
	{
		extent.close();
	}

	/*@Test(description="Compare 2 Cars")
	public void UseCase1(Method m) throws Exception
	{		
		test=extent.createTest("Compare 2 Cars");
		log.info("Testcase "+m.getName()+" execution started");
		ConfigValues.TestCaseName=m.getName();
		selectcarspage.verifypageTitle(test, "Compare Cars - New Car Comparisons in Singapore | Oto");
		selectcarspage.verifypageHeading(test,"Compare Cars");	
		selectcarspage.verifyBreadCrums(test,"Home/New Cars/Compare");
		selectcarspage.selectCarCardDetails(test,1,"brand","Toyota");
		selectcarspage.selectCarCardDetails(test,1,"model","Corolla Altis");
		selectcarspage.selectCarCardDetails(test,1,"variant","Standard");
		selectcarspage.selectCarCardDetails(test,2,"brand","Hyundai");
		selectcarspage.selectCarCardDetails(test,2,"model","Ioniq Electric");
		selectcarspage.selectCarCardDetails(test,2,"variant","Electric SR");
		comparecarspage=selectcarspage.clickCompareCars(test);
		comparecarspage.verifypageTitle(test, "Toyota Corolla Altis vs Hyundai Ioniq Electric | Oto");
		selectcarspage.verifyBreadCrums(test,"Home/New Cars/Compare Cars/Toyota Corolla Altis vs Hyundai Ioniq Electric");
		comparecarspage.verifypageHeading(test,"Toyota Corolla Altis vs Hyundai Ioniq Electric Comparison\r\n" + 
				"");
		comparecarspage.compareCardDetails(test,"Toyota Corolla Altis", "$97,888","Hyundai Ioniq Electric", "$167,999");		
		comparecarspage.verifyfeatureList1(test,"TOYOTA COROLLA ALTIS","HYUNDAI IONIQ ELECTRIC");
	}*/
	
	@Test(description="Add & Remove Third car for comparison")
	public void UseCase2(Method m) throws Exception
	{		
		test=extent.createTest("Compare 2 Cars");
		log.info("Testcase "+m.getName()+" execution started");
		ConfigValues.TestCaseName=m.getName();
		selectcarspage.verifypageTitle(test, "Compare Cars - New Car Comparisons in Singapore | Oto");
		selectcarspage.verifypageHeading(test,"Compare Cars");	
		selectcarspage.verifyBreadCrums(test,"Home/New Cars/Compare");
		selectcarspage.selectCarCardDetails(test,1,"brand","Toyota");
		selectcarspage.selectCarCardDetails(test,1,"model","Corolla Altis");
		selectcarspage.selectCarCardDetails(test,1,"variant","Standard");
		selectcarspage.selectCarCardDetails(test,2,"brand","Hyundai");
		selectcarspage.selectCarCardDetails(test,2,"model","Ioniq Electric");
		selectcarspage.selectCarCardDetails(test,2,"variant","Electric SR");
		comparecarspage=selectcarspage.clickCompareCars(test);
		comparecarspage.verifypageTitle(test, "Toyota Corolla Altis vs Hyundai Ioniq Electric | Oto");
		selectcarspage.verifyBreadCrums(test,"Home/New Cars/Compare Cars/Toyota Corolla Altis vs Hyundai Ioniq Electric");
		comparecarspage.verifypageHeading(test,"Toyota Corolla Altis vs Hyundai Ioniq Electric Comparison\r\n" + 
				"");
		comparecarspage.compareCardDetails(test,"Toyota Corolla Altis", "$97,888","Hyundai Ioniq Electric", "$167,999");		
		comparecarspage.verifyfeatureList1(test,"TOYOTA COROLLA ALTIS","HYUNDAI IONIQ ELECTRIC");
		selectcarspage.selectCarCardDetails(test,3,"brand","Toyota");
		selectcarspage.selectCarCardDetails(test,3,"model","Fortuner");
		selectcarspage.selectCarCardDetails(test,3,"variant","2.7L");
		
		selectcarspage.selectCarCardDetails(test,4,"brand","Volvo");
		selectcarspage.selectCarCardDetails(test,4,"model","V60");
		selectcarspage.selectCarCardDetails(test,4,"variant","T4 Momentum");
		
		comparecarspage.removeCompareCar(test,3);
		comparecarspage.removeCompareCar(test,3);
	}


}
