package practice;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;


public class VerticalSwipeUsingAction extends TestBase{

//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	CustomFunction cf=new CustomFunction();
	
	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		//click on views
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();
		Thread.sleep(3000);
		//WebElement swipeArea=driver.findElement(AppiumBy.id("android:id/list"));
		By elem=By.id("android:id/list");
		cf.swipeDown(elem,"List of option inside VIEW");
		
		//perform vertical swipe and search WebView3
//		while(getWebView3().size()==0)
//		{
//			base.verticalSwipe();
//		}
//		if(getWebView3().size()>0)
//		{
//				getWebView3().get(0).click();
//		}
//		Thread.sleep(5000);
//		
//		
	}
//	public List<WebElement> getWebView3()
//	{
//		List<WebElement> list=driver.findElements(AppiumBy.xpath("//android.widget.TextView[@content-desc='WebView3']"));
//		return list;
//	}
}
