package practice;

import java.net.MalformedURLException;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.TestBase;
import utils.DriverFactory;

public class AppiumDeviceInteractions extends TestBase
{
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		
		DriverFactory.getInstance().getDriver().lockDevice();
		Thread.sleep(5000);
	//	testStep="Device locked";
		try
		{
			boolean st=DriverFactory.getInstance().getDriver().isDeviceLocked();
			Assert.assertEquals(st, true);
		}
		catch(Throwable e)
		{
//			ExtentLoging.infoLog(testStep+" Failed", test);
//			Log4jSetup.log(testStep+" Failed");
		}
		Thread.sleep(5000);
		DriverFactory.getInstance().getDriver().unlockDevice();
		
		Thread.sleep(5000);
		ScreenOrientation orientation=DriverFactory.getInstance().getDriver().getOrientation();
		System.out.println(orientation);
		
		Thread.sleep(3000);
		DriverFactory.getInstance().getDriver().rotate(ScreenOrientation.LANDSCAPE);
		
		Thread.sleep(3000);
		System.out.println("----------------------------------------------------");
		
		DeviceRotation dr=DriverFactory.getInstance().getDriver().rotation();		
		System.out.println("Device Rotation : "+dr);
		
		
		



	}
}
