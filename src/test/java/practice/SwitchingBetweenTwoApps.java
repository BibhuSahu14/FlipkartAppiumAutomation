package practice;

import java.net.MalformedURLException;

import org.testng.annotations.Test;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import testBase.TestBase;
import utils.DriverFactory;

public class SwitchingBetweenTwoApps extends TestBase
{
//		driver=base.openAppProgramaticallyStartServer("com.google.android.deskclock","com.android.deskclock.DeskClock");

	
	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		Thread.sleep(3000);
	
		//fetch the clock time
		String time=DriverFactory.getInstance().getDriver().findElement(AppiumBy.id("com.google.android.deskclock:id/digital_clock")).getText();
		System.out.println(time);
		
		//Switching to another app
//		driver.executeScript("mobile:startActivity", ImmutableMap.of("intent","com.android.settings/.Settings"));
		DriverFactory.getInstance().getDriver().startActivity(new Activity("com.android.settings", "com.android.settings.Settings"));
		Thread.sleep(3000);
		
		//click on Network & internet
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Network & internet']")).click();
		
	}
}
