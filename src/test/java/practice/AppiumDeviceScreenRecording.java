package practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;


public class AppiumDeviceScreenRecording extends TestBase
{
//		E:\\APK files\\APKFiles-1\\resources\\ApiDemos-debug.apk"

	CustomFunction cf=new CustomFunction();
	
	@Test(description="*****")
	public void test() throws IOException
	{
	
		//Start recording
		DriverFactory.getInstance().getDriver().startRecordingScreen();
		
		//click on dropdown
		DriverFactory.getInstance().getDriver().findElement(By.id("android:id/text1")).click();
		
		//select option from dropdown
		cf.scrollToElement("Burundi");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@text='Burundi']")).click();
		
		//Enter name in textbox
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bibhu");

		//Hide Keyboard
		cf.closeKeyboard();

		//click on female radio button
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		//stop recording
		String base64String=DriverFactory.getInstance().getDriver().stopRecordingScreen();
		
		//build our video using string returned from stop recording method
		byte[] data=Base64.getDecoder().decode(base64String);
		String destinationPath="E:\\video\\Firstvideo.mp4";
		File path= new File(destinationPath);
		FileOutputStream fos=new FileOutputStream(path);
		fos.write(data);
		fos.close();
	}
}
