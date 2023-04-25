package practice;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;

public class ContextHandle extends TestBase 
{

	CustomFunction cf=new CustomFunction();
	//"E:\\APK files\\APKFiles-1\\resources\\General-Store.apk"
	@Test(description="*****")
	public void test() throws MalformedURLException, InterruptedException
	{
		
		//click on dropdown
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		
		//select option from dropdown
		cf.scrollToElement("Burundi");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@text='Burundi']")).click();
		
		//Enter name in textbox
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bibhu");

		//Hide Keyboard
		cf.closeKeyboard();

		//click on female radio button
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		//click on let's shop
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//wait for the products screen to show up
		//base.waitForVisibility(By.id("com.androidsample.generalstore:id/toolbar_title"), 10);
		
		//scroll to Jordan Lift Off
//		WebElement shoe=driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan Lift Off']"));
		cf.scrollToElement("Jordan Lift Off");
		
		//Select Jordan Lift Off add to cart
		List<WebElement> eles=DriverFactory.getInstance().getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']"));
		int count=eles.size();
		System.out.println(count);
		for(int i=0;i<count;i++)
		{
			System.out.println("hello");
			String shoeName=DriverFactory.getInstance().getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")).get(i).getText();
			System.out.println(shoeName);
			if(shoeName.equalsIgnoreCase("Jordan Lift Off"))
			{
				DriverFactory.getInstance().getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(i).click();
				System.out.println("Clicked on add to cart");
				break;
			}
		}
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(10000);
		//Context Handling
		Set<String> contextNames= DriverFactory.getInstance().getDriver().getContextHandles();
		for(String contextName : contextNames)
		{
			System.out.println(contextName);
		}
				
		DriverFactory.getInstance().getDriver().context("WEBVIEW_com.androidsample.generalstore");
		Thread.sleep(5000);
		
		//upto this the switching context handle is done
		
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.xpath("//android.widget.EditText")).click();
		
		DriverFactory.getInstance().getDriver().findElement(AppiumBy.xpath("//android.widget.EditText")).sendKeys("Mindfire");
		
		DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
		
		
	}
}
