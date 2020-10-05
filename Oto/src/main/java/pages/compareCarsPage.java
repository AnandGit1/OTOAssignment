package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExcelReader;
import utils.LoggerHelper;

public class compareCarsPage {

	
	
	private Logger log = LoggerHelper.getLogger(compareCarsPage.class);
	WebDriver driver;
	ExtentTest test;
	ExcelReader testData;

	@FindBy(xpath="//*[@id=\"comp-page\"]//h1")
	public
	WebElement pageHeading;

	@FindBy(xpath=("//*[@id=\"compare-form\"]/div/div[1]/div[1]/a"))
	public
	WebElement car1Name;

	@FindBy(xpath=("//*[@id=\"compare-form\"]/div/div[1]/div[1]/div[1]"))
	public
	WebElement car1Price;

	@FindBy(xpath=("//*[@id=\"compare-form\"]/div/div[1]/div[2]/a"))
	public
	WebElement car2Name;

	@FindBy(xpath=("//*[@id=\"compare-form\"]/div/div[1]/div[2]/div[1]"))
	public
	WebElement car2Price;
	
	@FindBy(xpath="//*[@id=\"compare-feature1\"]/thead//th[1]")
	public
	WebElement feature1TableOverview;
	
	@FindBy(xpath="//*[@id=\"compare-feature1\"]/thead//th[2]")
	public
	WebElement feature1TableCar1Name;
	
	@FindBy(xpath="//*[@id=\"compare-feature1\"]/thead//th[3]")
	public
	WebElement feature1TableCar2Name;	

	public compareCarsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		testData = new ExcelReader();
	}
	
	public void verifypageHeading(ExtentTest test,String spageHeading) 
	{
		if(pageHeading.getText().trim().equalsIgnoreCase(spageHeading.trim())) 
		{
			test.log(Status.PASS,"Page Heading displayed as "+spageHeading); 
		}
		else 
		{
			test.log(Status.FAIL,"Page Heading MISMATCH , expected is "+spageHeading
					+"but displayed as"+pageHeading.getText()); 
		}

	}


	public void compareCardDetails(ExtentTest test,String sCarName1,String sCarprice1,String sCarName2,String sCarprice2)
	{
		
		if(car1Name.getText().equals(sCarName1))
		{
			test.log(Status.PASS,"Card 1 Car Name "+sCarName1+" validated sucessfully");
		}
		else
		{		
			test.log(Status.FAIL,"Card 1 Car Name is not matching, ACTUAL car name displayed "+car1Name.getText()+" Expected Car Name "+sCarName1);
		}
	
		if(car1Price.getText().equals(sCarprice1))
		{
			test.log(Status.PASS,"Card 1 Car Price "+sCarprice1+" validated sucessfully");
		}
		else
		{		
			test.log(Status.FAIL,"Card 1 Car Price is not matching, ACTUAL car price displayed "+car1Price.getText()+" Expected Car price "+sCarprice1);
		}
	
		
		if(car2Name.getText().equals(sCarName2))
		{
			test.log(Status.PASS,"Card 2 Car Name "+sCarName2+" validated sucessfully"); 
		}
		else
		{		
			test.log(Status.FAIL,"Card 2 Car Name is not matching, ACTUAL car name displayed "+car2Name.getText()+" Expected Car Name "+sCarName2);
		}
	
		if(car2Price.getText().equals(sCarprice2))
		{
			test.log(Status.PASS,"Card 2 Car Price "+sCarprice2+" validated sucessfully");
		}
		else
		{		
			test.log(Status.FAIL,"Card 2 Car price is not matching, ACTUAL car price displayed "+car2Price.getText()+" Expected Car Name "+sCarprice2);
		}
		
	}
	
	public void verifypageTitle(ExtentTest test,String sPageTitle) 
	{
		utils.GenericHelpers.Verification(driver,
				sPageTitle, 
				driver.getTitle(),
				test, 
				"Compare cars page title ");		
	}

	public void verifyfeatureList1(ExtentTest test,String car1,String car2) 
	{
		
		utils.GenericHelpers.Verification(driver,
				testData.getColName("Feature1", 0), 
				feature1TableOverview.getText(),
				test, 
				"Feature1 Table heading column1 ");

		utils.GenericHelpers.Verification(driver,
				testData.getColName("Feature1", 1),
				feature1TableCar1Name.getText(),
				test, 
				"Feature1 Table heading column2 ");

		utils.GenericHelpers.Verification(driver,
				testData.getColName("Feature1", 2), 
				feature1TableCar2Name.getText(),
				test, 
				"Feature1 Table heading column3 ");


		//======* Brand*======//	
		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", "OVERVIEW",testData.findRow("Feature1", "Brand")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[1]/td[1]")).getText(),
				test, 
				"Feature1 Table Brand value ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car1,testData.findRow("Feature1", "Brand")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[1]/td[2]")).getText(),
				test, 
				"Feature1 Table Car1 ( "+car1+" ) Fuel Type column ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car2,testData.findRow("Feature1", "Brand")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[1]/td[3]")).getText(),
				test, 
				"Feature1 Table Car2 ( "+car2+" ) Fuel Type column ");



		//======* Off Road Price*======//	

		utils.GenericHelpers.Verification(driver, testData.getCellData("Feature1",
				"OVERVIEW",testData.findRow("Feature1", "Off Road Price* (Singapore)")+1).trim(),
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[2]/td[1]/div[1]")).getText()+" "+
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[2]/td[1]/div[2]")).getText()
				, test,
				"Feature1 Table Off Road Price* value ");

		/*
		 * utils.GenericHelpers.Verification(driver, testData.getCellData("Feature1",
		 * car1,testData.findRow("Feature1",
		 * "Off Road Price* (Singapore)")+1).trim(),
		 * driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[2]/td[2]"
		 * )).getText().trim(), test,
		 * "Feature1 Table Car2 ( "+car1+" ) Off Road Price* column "
		 * );
		 * 
		 * utils.GenericHelpers.Verification(driver, testData.getCellData("Feature1",
		 * car2,testData.findRow("Feature1",
		 * "Off Road Price* (Singapore)")+1),
		 * driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[2]/td[3]"
		 * )).getText(), test,
		 * "Feature1 Table Car2 ( "+car2+" - ) Off Road Price* column "
		 * );
		 */


		//======* User Rating*======//	
				utils.GenericHelpers.Verification(driver,
						testData.getCellData("Feature1", "OVERVIEW",testData.findRow("Feature1", "User Rating")+1), 
						driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[3]/td[1]")).getText(),
						test, 
						"Feature1 Table User Rating value ");

				utils.GenericHelpers.Verification(driver,
						testData.getCellData("Feature1", car1,testData.findRow("Feature1", "User Rating")+1), 
						driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[3]/td[2]/div/div")).getText(),
						test, 
						"Feature1 Table Car1 ( "+car1+" ) User Rating column ");

				utils.GenericHelpers.Verification(driver,
						testData.getCellData("Feature1", car2,testData.findRow("Feature1", "User Rating")+1), 
						driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[3]/td[3]/div/div")).getText(),
						test, 
						"Feature1 Table Car2 ( "+car2+" ) User Rating column ");

				//======* Available Colors*======//	
				utils.GenericHelpers.Verification(driver,
						testData.getCellData("Feature1", "OVERVIEW",testData.findRow("Feature1", "Available Color(s)")+1), 
						driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[4]/td[1]")).getText(),
						test, 
						"Feature1 Table User Rating value ");

		/*
		 * utils.GenericHelpers.Verification(driver, testData.getCellData("Feature1",
		 * car1,testData.findRow("Feature1",
		 * "Available Color(s)")+1), Integer.toString(driver.findElements(By.xpath(
		 * "//*[@id=\"compare-feature1\"]/tbody/tr[4]/td[2]/div/span")).size()), test,
		 * "Feature1 Table Car2 ( "+car1+" ) User Rating column ");
		 * 
		 * utils.GenericHelpers.Verification(driver, testData.getCellData("Feature1",
		 * car2,testData.findRow("Feature1", "Available Color(s)")+1),
		 * Integer.toString(driver.findElements(By.xpath(
		 * "//*[@id=\"compare-feature1\"]/tbody/tr[4]/td[3]/div/span")).size()), test,
		 * "Feature1 Table Car2 ( "+car2+" ) Available Colors column ");
		 */


		//======* fuel type*======//	

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", "OVERVIEW",testData.findRow("Feature1", "Fuel Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[5]/td[1]")).getText(),
				test, 
				"Feature1 Table Fuel Type ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car1,testData.findRow("Feature1", "Fuel Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[5]/td[2]")).getText(),
				test, 
				"Feature1 Table Car1 ( "+car1+" ) Fuel Type column ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car2,testData.findRow("Feature1", "Fuel Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[5]/td[3]")).getText(),
				test, 
				"Feature1 Table Car2 ( "+car2+" ) Fuel Type column ");

		//======* vehicle type*======//	

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", "OVERVIEW",testData.findRow("Feature1", "Vehicle Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[6]/td[1]")).getText(),
				test, 
				"Feature1 Table Fuel Type ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car1,testData.findRow("Feature1", "Vehicle Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[6]/td[2]")).getText(),
				test, 
				"Feature1 Table Car1 ( "+car1+" ) Fuel Type column ");

		utils.GenericHelpers.Verification(driver,
				testData.getCellData("Feature1", car2,testData.findRow("Feature1", "Vehicle Type")+1), 
				driver.findElement(By.xpath("//*[@id=\"compare-feature1\"]/tbody/tr[6]/td[3]")).getText(),
				test, 
				"Feature1 Table Car2 ( "+car2+" ) Vehicle Type column ");		
	}
	
	public void removeCompareCar(ExtentTest test,int CarCardNum)
	{
		//String removelocator="icon-remove close"+(CarCardNum-1);
		driver.findElement(By.xpath("//*[@id=\"compare-form\"]/div/div/div["+(CarCardNum-1)+"]/i")).click();
		test.log(Status.PASS,"Removed the CarCardnumber "+CarCardNum+" from cars comparision");
	}
	
}