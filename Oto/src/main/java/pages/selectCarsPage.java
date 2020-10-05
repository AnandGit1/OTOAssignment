package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.LoggerHelper;

public class selectCarsPage {

	private Logger log = LoggerHelper.getLogger(selectCarsPage.class);
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath="//*[@class=\"breadcrumb main\"]/ul")
	public
	WebElement breadCrums;


	@FindBy(xpath="//*[@class=\"compare page\"]//h1") 
	public
	WebElement pageHeading;

	//WebElement pageHeading1=driver.findElement(By.xpath("//*[@class=\"compare page\"]//h1"));


	@FindBy(xpath="//*[@id=\"compare-form\"]/div/div[1]")
	public
	WebElement firstCar;

	@FindBy(xpath="//*[@id=\"compare-form\"]/div/div[2]")
	public
	WebElement secondCar;

	@FindBy(xpath="//*[@id=\"compare-form\"]/div/button")
	public
	WebElement btnCompareCars;

	@FindBy(id="brand0")
	public
	WebElement firstCarBrand;

	@FindBy(id="model0")
	public
	WebElement firstCarModel;

	@FindBy(id="variant0")
	public
	WebElement firstCarVariant;	

	@FindBy(id="brand1")
	public
	WebElement secondCarBrand;

	@FindBy(id="model1")
	public
	WebElement secondCarModel;

	@FindBy(id="variant1")
	public
	WebElement secondCarVariant;	



	public selectCarsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);				
	}	

	public void verifypageTitle(ExtentTest test,String sPageTitle) 
	{
		utils.GenericHelpers.Verification(driver,
				sPageTitle, 
				driver.getTitle(),
				test, 
				"Select Cars page tile ");		
	}

	public void verifypageHeading(ExtentTest test,String spageHeading) 
	{
		if(pageHeading.getText().equals(spageHeading)) 
		{
			test.log(Status.PASS,"Page Heading displayed as "+spageHeading); 
		}
		else 
		{
			test.log(Status.FAIL,"Page Heading MISMATCH , expected is "+spageHeading
					+"but displayed as"+pageHeading.getText()); 
		}

	}

	public void verifyBreadCrums(ExtentTest test,String sexpectedbreadCrum)
	{
		List<String> lpagebreadCrums=new ArrayList<String>();
		String lexpbreadCrums[];
		lexpbreadCrums=sexpectedbreadCrum.split("/");
		List<WebElement> wpagebreadCrums=breadCrums.findElements(By.tagName("li"));
		for(WebElement breadcrum : wpagebreadCrums)
		{
			lpagebreadCrums.add(breadcrum.getText());
		}

		if(lpagebreadCrums.size()==lexpbreadCrums.length)
		{

			for(int i=0;i<lpagebreadCrums.size();i++) 
			{
				if(lpagebreadCrums.get(i).equals(lexpbreadCrums[i]))
				{
					test.log(Status.PASS,"Breadcrum at position "+i+" value is "+lpagebreadCrums.get(i));
				}
				else
				{
					test.log(Status.FAIL,"Expected value of Breadcrum at position "+i+" is "+lexpbreadCrums[i]+
							"but found "+lpagebreadCrums.get(i));
				}
			}
		}
		else 
		{
			test.log(Status.FAIL,"Breadcrum is not matching"); 
		} 

	}	

	public void selectCarCardDetails(ExtentTest test,int cardNum,String selectType,String selectValue) throws Exception
	{
		Boolean found=false;
		switch(selectType)
		{
		case "brand":
			driver.findElement(By.id("brand"+(cardNum-1))).click();
			Thread.sleep(2000);			
			found=selectCardValue(selectValue);
			break;

		case "model":
			driver.findElement(By.id("model"+(cardNum-1))).click();
			Thread.sleep(2000);			
			found=selectCardValue(selectValue);
			break;

		case "variant":
			driver.findElement(By.id("variant"+(cardNum-1))).click();
			Thread.sleep(2000);			
			found=selectCardValue(selectValue);
			break;		
		
		}

		if(found.equals(true))
		{
			test.log(Status.PASS,"Card  "+cardNum+" "+selectType+" is selected with value "+selectValue);
		}
		else
		{
			test.log(Status.FAIL,"Unable to select Card "+cardNum+" "+selectType+" with value "+selectValue);
		}

	}

	public compareCarsPage clickCompareCars(ExtentTest test)
	{
		btnCompareCars.click();
		test.log(Status.INFO,"Clicked on Compare Cars button");
		return new compareCarsPage(driver);
	}


	public Boolean selectCardValue(String selectValue) throws Exception
	{
		Boolean found = false;
		List<WebElement> brands = driver.findElements(By.xpath("//*[@class=\"gs_ta_results\"]/ul/li"));
		for(WebElement item : brands)
		{
			if(item.getText().equals(selectValue))
			{
				item.click();
				Thread.sleep(2000);
				found=true;
				return found;
			}
		}
		return found;
	}

}
