package practice;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import testBase.TestBase;
import utils.DriverFactory;


public class SwitchAndCheckBox extends TestBase{
	
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk
	
	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		//click on Preference
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Preference")).click();
		
		//click on 9. Switch
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("9. Switch")).click();
		
		//click on check box
		WebElement checkbox=DriverFactory.getInstance().getDriver().findElement(By.id("android:id/checkbox"));
		checkbox.click();
		
		//verify is the checkbox got checked
		Assert.assertEquals("true", checkbox.getAttribute("checked"));
		
		//enable Switch Preference
		WebElement switchPreference=DriverFactory.getInstance().getDriver().findElement(By.xpath("(//android.widget.Switch)[1]"));
		switchPreference.click();
		
		//Verify switch preference got enabled
		Assert.assertEquals("ON", switchPreference.getAttribute("text"));
		
		//enable switch Preference with custom text
		WebElement spwCustomText=DriverFactory.getInstance().getDriver().findElement(By.xpath("(//android.widget.Switch)[2]"));
		spwCustomText.click();
		
		//Verify switch preference with custom text got enabled
		Assert.assertEquals("NO", spwCustomText.getAttribute("text"));
		
	}

}
