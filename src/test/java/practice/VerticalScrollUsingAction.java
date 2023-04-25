package practice;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;

public class VerticalScrollUsingAction extends TestBase 
{
//	E:\\APK files\\APKFiles-1\\resources\\General-Store.apk

CustomFunction cf=new CustomFunction();
By actionAreaLocator=By.xpath("//android.widget.ListView");
By elementLocator=By.xpath("//android.widget.TextView[@text='Burundi']");

@Test(description="*****")
public void test() throws MalformedURLException, InterruptedException
{
	
	//click on dropdown
	DriverFactory.getInstance().getDriver().findElement(By.id("android:id/text1")).click();
	
	//select option from dropdown
	cf.verticalScrollDownToElementUsingAction(actionAreaLocator, elementLocator,"Burundi");
	DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@text='Burundi']")).click();
	
	//Enter name in textbox
	DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bibhu");

	//Hide Keyboard
	cf.closeKeyboard();

	//click on female radio button
	DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	
	
	
	
}
}
