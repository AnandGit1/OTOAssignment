package utils;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;

public class LoggerHelper {
	private static boolean root=false;
	
	static String configFilename=System.getProperty("user.dir")+"\\src\\main\\java\\Config\\log4j.properties";
	
	//public static final Logger logger = Logger.getLogger(LoggerHelper.class.getName());
	
	public static Logger getLogger(Class cls)
	{
		if(root){
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(configFilename);
		root = true;
		return Logger.getLogger(cls);
	}	
	
}