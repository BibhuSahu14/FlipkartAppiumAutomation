package practice;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;
import testBase.TestBase;
import utils.DriverFactory;

public class AppiumDeviceKeys extends TestBase
{
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk

	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		//click on views
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Views")).click();
		
		//click on Animation
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Animation")).click();
		
		//click on Shake
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.accessibilityId("Shake")).click();
		
		//click on input field
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.id("io.appium.android.apis:id/pw")).click();
		
		//check is keyboard shown
		boolean st=DriverFactory.getInstance().getDriver().isKeyboardShown();
		System.out.println("Is keyboard shown "+st);
		
		//click capital B through keyboard
		DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.B).withMetaModifier(KeyEventMetaModifier.SHIFT_LEFT_ON));
		DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.ENTER).withFlag(KeyEventFlag.EDITOR_ACTION));
		//click i through keyboard
		DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.I));
		
		Thread.sleep(5000);
		
		//close keyboard
		DriverFactory.getInstance().getDriver().hideKeyboard();
		
		//check Keyboard is shown
		boolean st2=DriverFactory.getInstance().getDriver().isKeyboardShown();
		System.out.println("Is keyboard shown "+st2);
		
		//Virtual device powering off
		DriverFactory.getInstance().getDriver().longPressKey(new KeyEvent(AndroidKey.POWER));
		
		Thread.sleep(5000);
		
		//click on power off button
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text='Power off']")).click();
		
		
	}
}
