package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReportsHelper {

	static String ExtentFileDir=System.getProperty("user.dir")+"\\extent-reports\\";
	static String ScreenShotdir=System.getProperty("user.dir")+"\\extent-reports\\screenshots\\";

	public static void UpdateResult(WebDriver driver,ITestResult testResults,ExtentTest test)
	{		
		
		if(testResults.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, testResults.getThrowable());
			createAttachScreenshot(driver,test);		

		}
		else if(testResults.getStatus()==ITestResult.SUCCESS)
		{
			//test.log(Status.PASS, "Test case is Passed");			
		}
		else if(testResults.getStatus()==ITestResult.SKIP)
		{
			//test.log(Status.SKIP, testResults.getThrowable());			
		}		
		
	}

	public static void createAttachScreenshot(WebDriver driver,ExtentTest test)
	{
		File scrShot,DestFileloc;
		try 
		{
			scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			DestFileloc=new File(GetScreenshotPath());
			Files.copy(scrShot.toPath(),DestFileloc.toPath());	
			test.fail("Snapshot created in the location : "+ DestFileloc + "  "+test.addScreenCaptureFromPath(DestFileloc.toString()));
			utils.GenericHelpers.wait(2);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static String getReportspath(String classname)
	{
		File Extentpath=new File(ExtentFileDir+classname);
		String filepath=null;
		if(!Extentpath.exists())
		{
			createdir(Extentpath);
		}
		filepath=Extentpath+"\\"+utils.GenericHelpers.getDate("yyyyMMdd_HHmmss")+".html";
		System.out.println(filepath);
		return filepath;
	}

	public static void createdir(File dir)
	{		
		if(!dir.exists()){
			try {
				dir.mkdir();
			}
			catch(Exception e) 
			{
				System.out.println("ecception");
			}
		}

	}

	public static String GetScreenshotPath()
	{
		String Screenshotpath=null;
		File Extentpath=new File(ScreenShotdir);
		if(!Extentpath.exists())
		{
			createdir(Extentpath);
		}

		Screenshotpath=ScreenShotdir+utils.GenericHelpers.getDate("yyyyMMdd_HHmmss")+".png";
		return Screenshotpath;

	}

}
