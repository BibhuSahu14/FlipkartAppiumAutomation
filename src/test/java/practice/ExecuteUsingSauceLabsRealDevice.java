package practice;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import reusableFunctions.CustomFunction;

public class ExecuteUsingSauceLabsRealDevice 
{
	AndroidDriver driver;
	URL url;

	

	@BeforeMethod
	public void setUp(Method method) throws MalformedURLException
	{
		MutableCapabilities capabilities=new MutableCapabilities();
		MutableCapabilities sauceOptions=new MutableCapabilities();
		url=new URL("https://oauth-bibhuprasadsahu14-592e6:95fb7b0e-9ee6-4a05-97a9-be0684c85bf4@ondemand.eu-central-1.saucelabs.com:443/wd/hub");

		//find a device in the cloud
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appium:automationName", "UiAutomator2");
		//Allocate any avilable samsung device with Android version 10
		capabilities.setCapability("appium:deviceName", "Samsung Galaxy S9");
		capabilities.setCapability("appium:platformVersion", "10");
		String appName = "General-Store.apk";
		capabilities.setCapability("appium:app", "storage:filename=" +appName);
		capabilities.setCapability("appium:appWaitActivity","com.androidsample.generalstore.MainActivity");

		// Sauce capabilities
		sauceOptions.setCapability("name", method.getName());
		sauceOptions.setCapability("build", "GeneralStore-job-1");
		List<String> tags = Arrays.asList("sauceDemo", "Android", "Demo", "gestures");
		sauceOptions.setCapability("tags", tags);
		sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
		sauceOptions.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));

		capabilities.setCapability("sauce:options", sauceOptions);

		driver = new AndroidDriver(url, capabilities);

	}
	@Test
	public void verifyToastMessage() throws InterruptedException
	{
		
		CustomFunction cf=new CustomFunction();
		//click on dropdown
		driver.findElement(By.id("android:id/text1")).click();

		//select option from dropdown
		cf.scrollToElement("Burundi");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Burundi']")).click();

		//Enter name in textbox
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bibhu");
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).click();
		Thread.sleep(5000);

		//Hide Keyboard
		cf.closeKeyboard();

		Thread.sleep(5000);

		//click on female radio button
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();

		//click on let's shop
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		//verify whether the toast message is giving the correct message
		String toastmsg=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastmsg, "Please enter your name");

	}
	@AfterMethod
	public void teardown(ITestResult result)
	{
		String status = result.isSuccess() ? "passed" : "failed";
		driver.executeScript("sauce:job-result=" + status);
	
	}
}
