package practice;

import java.net.MalformedURLException;

import org.testng.annotations.Test;



import io.appium.java_client.AppiumBy;
import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;

public class HorizontalScrollToElement extends TestBase
{
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	CustomFunction cf=new CustomFunction();
	
	@Test(description="*****")
	public void test() throws MalformedURLException
	{

		//click on views
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Views")).click();

		//click on Gallery
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Gallery")).click();

		//click on Photos
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("1. Photos")).click();
		
		
		
		cf.scrollToElement(null);
		
	}
}
