package utils;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import src.test.java.testbase.TestBase;

public class Listener extends TestBase implements ITestListener{

	private Logger log = LoggerHelper.getLogger(Listener.class);

	public void onFinish(ITestContext Result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext Result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult Result) {
		// TODO Auto-generated method stub		
		log.error("**********Testcase Failed**********");

		File destFile = null;
		System.out.println(TestBase.driver);
		File screFile = ((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);

		try{
			destFile = new File(System.getProperty("user.dir")+"//screenshots//"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".png");
			Files.copy(screFile.toPath(),destFile.toPath());
			System.out.println("Screenshot during testcase fail is copied in location "+destFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//driver.close();
		driver.quit();

	}

	public void onTestSkipped(ITestResult Result) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult Result) {
		// TODO Auto-generated method stub
		log.info("**********Testcase Started**********");

	}

	public void onTestSuccess(ITestResult Result) {
		// TODO Auto-generated method stub
		log.info("**********Testcase Completed Sucessfully**********");
		//driver.close();
		driver.quit();

	}

}
