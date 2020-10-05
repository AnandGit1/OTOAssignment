package src.test.java.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.LoggerHelper;

public class TestBase {
	
	public static Properties prop;
	public static WebDriver driver;
	//public static DataBaseHelper dataBaseHelper;

	private static Logger log = LoggerHelper.getLogger(TestBase.class);

	public TestBase()
	{
		prop=new Properties();
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");		
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

	public static String getConfigValue(String propertyKey)
	{
		String propertyVal = null;
		try 
		{			
			propertyVal=prop.getProperty(propertyKey);
		}
		catch(Exception e)
		{
			log.info("Error in reading property value for the key "+propertyKey);
		}
		return propertyVal;		
	}


	public static WebDriver initialization()
	{
		
		//dataBaseHelper=new DataBaseHelper();

		if(prop.getProperty("browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//browsers//chromedriver.exe");  
			driver=new ChromeDriver();
			log.info("Chrome browser Opened ");
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//browsers//geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
			driver=new FirefoxDriver();
			log.info("Firefox browser initialization completed ");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwait")),TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTime")),TimeUnit.SECONDS);
		driver.get("https://www.oto.com.sg/compare-cars");
		return driver;
	}	


}
