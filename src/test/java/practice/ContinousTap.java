package practice;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;

public class ContinousTap extends TestBase
{
	By settingPageActionAreaLocator=By.id("com.android.settings:id/recycler_view");
	By aboutEmulatedDeviceLocator=By.xpath("//android.widget.TextView[@text='About emulated device']");
	By androidVersionLocator=By.xpath("//android.widget.TextView[@text='Android version']");
	
	CustomFunction cf=new CustomFunction();
	
	@Test(description="*****")
	public void test() throws InterruptedException
	{
		cf.verticalScrollDownToElementUsingAction(settingPageActionAreaLocator, aboutEmulatedDeviceLocator, "About Emulator Device");
		Thread.sleep(5000);
		cf.click_custom(aboutEmulatedDeviceLocator, "About Emulator Device");
		//cf.verticalScrollUsingJavaScriptExecutor(androidVersionLocator);
		cf.click_custom(androidVersionLocator, "Android Version");
		Thread.sleep(2000);
		PointerInput pointer1 = new PointerInput(Kind.TOUCH, "finger");
		
		Sequence sq=new Sequence(pointer1,0);
		Rectangle point=DriverFactory.getInstance().getDriver().findElement(androidVersionLocator).getRect();
		int centerX=point.x+point.width/2;
		int centerY=point.y+point.height/2;
		sq.addAction(pointer1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, centerY));
		sq.addAction(pointer1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		sq.addAction(pointer1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		sq.addAction(pointer1.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), centerX, centerY));
		sq.addAction(pointer1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		sq.addAction(pointer1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//		sq.addAction(pointer.createPointerDown(0));
//		sq.addAction(pointer.createPointerUp(0));
//		sq.addAction(pointer.createPointerDown(0));
//		sq.addAction(pointer.createPointerUp(0));
		DriverFactory.getInstance().getDriver().perform(Arrays.asList(sq));
		Thread.sleep(5000);
	}
}
