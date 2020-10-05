package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class GenericHelpers {


	/*
	 * Driver,ExpectedResults,ActualResult,ExtentTest,Extent Message
	 */
	public static void Verification(WebDriver driver,String Expected,String Actual,ExtentTest test,String Message)
	{
		try
		{
			if(Expected.equalsIgnoreCase(Actual))
			{
				test.log(Status.PASS, Message+" verification completed Sucessfully . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
			}
			else
			{				
				test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
				utils.ExtentReportsHelper.createAttachScreenshot(driver, test);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}

	public static void Verification(WebDriver driver,int Expected,int Actual,ExtentTest test,String Message)
	{
		try
		{
			if(Expected==Actual)
			{
				test.log(Status.PASS, Message+" verification completed Sucessfully . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
			}
			else
			{			
				test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
				utils.ExtentReportsHelper.createAttachScreenshot(driver, test);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}

	public static void Verification(WebDriver driver,Boolean Expected,Boolean Actual,ExtentTest test,String Message)
	{
		try
		{
			if(Expected==Actual)				
			{					
				test.log(Status.PASS, Message+" verification completed Sucessfully . <br />  Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
			}
			else
			{
				test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
				utils.ExtentReportsHelper.createAttachScreenshot(driver, test);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}	

	public static void Verification(WebDriver driver, ExtentTest test, String string) 
	{		

		test.log(Status.INFO,"====="+string.toUpperCase()+"=====");

	}	

	/**
	 * @test - Extent Reports
	 * @field - WebElement
	 * @elementlist - List of Webelemnts (Li)
	 * @text - Value to be entered
	 * @fieldname - Only Field name for updating in reports
	 */
	public static void AutofillValue(ExtentTest test,WebElement field,WebElement elementlist,String text,String loginCancel,String fieldname) throws Exception
	{
		List<WebElement> matchinglist;
		boolean found=false;


		field.sendKeys(text);
		Thread.sleep(5000);


		if(loginCancel.equalsIgnoreCase("login"))
		{
			//LoginPage.EnterLoginCredentials(test,ConfigValues.UserName,ConfigValues.Password);
		}

		matchinglist =elementlist.findElements(By.tagName("li"));

		for(WebElement item : matchinglist)
		{
			if(item.getText().contains(text))
			{
				item.click();
				found=true;				
				break;
			}
		}
		if(found)
		{
			test.log(Status.INFO, fieldname+" is entered with "+text);
		}
		else
		{
			test.log(Status.FAIL,"On "+fieldname+" Could not find the "+text);
		}		
	}	
	public static void wait(int duration)
	{
		try {			
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void switchtab(WebDriver driver,String tabtype) throws Exception
	{
		Thread.sleep(5000);
		if(tabtype.equals("child"))
		{
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
		}
		else if(tabtype.equals("main")) 
		{
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));
		}
	}	

	public int randomNumberGeneration(int min, int max) {
		int randomNum;
		Random rand = new Random();
		randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}


	public static String getDate(String format)
	{
		String Date=null;
		switch(format)
		{
		case "yyyyMMdd_HHmmss":
			Date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			break;

		case "yyyyMMdd_HHmm":
			Date = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
			break;

		case "yyyyMMdd":
			Date = new SimpleDateFormat("yyyyMMdd").format(new Date());
			break;

		}
		return Date;		
	}

	public static List<String> getLiElements(ExtentTest test,WebElement webelement)
	{
		List<String> Elementlist=new ArrayList<String>();
		List<WebElement> liElementList=webelement.findElements(By.tagName("li"));
		for (WebElement liItem : liElementList)
		{
			Elementlist.add(liItem.getText());
			System.out.println("Item displayed is "+liItem.getText());
		}
		return Elementlist;
	}
	
	public static boolean compareLists(List<String> Expected,List<String> Actual,ExtentTest test)
	{
		boolean matching = false;
		if(Expected.size()!=Actual.size())
		{
			test.log(Status.FAIL,"Size of comparison arrays are not matching . <br />  Expected value -  " +Expected.size() + " <br /> Actual Result -  "+Actual.size());			
			return matching;
		}
		for(int i=0;i<Expected.size();i++)
		{
			if(!Expected.get(i).equalsIgnoreCase(Actual.get(i)))
			{
				test.log(Status.FAIL, "Expected value"+Expected.get(i)+" is not matching with the <br /> actual value"+Actual.get(i));
				matching=false;
			}	
			else
			{
				test.log(Status.PASS, "Verified the field "+Expected.get(i)+" is available");
				matching=true;
			}
		}
		return matching;		
	}
	

	public static void Assert(WebDriver driver,String Expected,String Actual,ExtentTest test,String Message)
	{
		if(Expected.equals(Actual))
		{
			test.log(Status.PASS, Message+" verification completed Sucessfully . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
		}
		else
		{				
			test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
			Assert.assertEquals(Actual, Expected);
		}
	}
	
	public static void Assert(WebDriver driver,boolean Expected,boolean Actual,ExtentTest test,String Message)
	{
		if(Expected==Actual)
		{
			test.log(Status.PASS, Message+" verification completed Sucessfully . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
		}
		else
		{				
			test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
			Assert.assertEquals(Actual, Expected);
		}
	}
	
	public static void Assert(WebDriver driver,int Expected,int Actual,ExtentTest test,String Message)
	{
		if(Expected==Actual)
		{
			test.log(Status.PASS, Message+" verification completed Sucessfully . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual);
		}
		else
		{				
			test.log(Status.FAIL, Message+" verification failed . <br /> Expected value -  " +Expected + " <br /> Actual Result -  "+Actual );
			Assert.assertEquals(Actual, Expected);
		}
	}


}
