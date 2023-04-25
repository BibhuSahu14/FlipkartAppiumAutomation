package practice;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.android.SupportsNetworkStateManagement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import testBase.TestBase;
import utils.DriverFactory;

public class AppiumDeviceNetwork extends TestBase
{
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	@Test(description="*****")
	public void test() throws MalformedURLException
	{
		
		//click on Home button
		DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.HOME));
		
		//Toggle Wifi
		((SupportsNetworkStateManagement)DriverFactory.getInstance().getDriver()).toggleWifi();
		
		//Toggle Data
		((SupportsNetworkStateManagement)DriverFactory.getInstance().getDriver()).toggleData();
		
		//Toggle Airplane Mode on
		//((SupportsNetworkStateManagement)DriverFactory.getInstance().getDriver()).toggleAirplaneMode();
		
		//Toggle location service
		DriverFactory.getInstance().getDriver().toggleLocationServices();
		
		
		
		
		
	}
}
