package practice;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;

public class VerticalSwipe extends TestBase{


//	E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	CustomFunction cf=new CustomFunction();
	
	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		//click on views
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();
		Thread.sleep(3000);
		By swipeArea=By.id("android:id/list");
		
		//perform vertical swipe and search WebView3
		while(getWebView3().size()==0)
		{
			cf.verticalSwipeusingJavaScriptExecutor(swipeArea);
		}
		if(getWebView3().size()>0)
		{
				getWebView3().get(0).click();
		}
		Thread.sleep(5000);
		
		
	}
	public List<WebElement> getWebView3()
	{
		List<WebElement> list=DriverFactory.getInstance().getDriver().findElements(AppiumBy.xpath("//android.widget.TextView[@content-desc='WebView3']"));
		return list;
	}

}
