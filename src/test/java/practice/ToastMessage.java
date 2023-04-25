package practice;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusableFunctions.CustomFunction;
import testBase.TestBase;
import utils.DriverFactory;



public class ToastMessage extends TestBase {

	CustomFunction cf=new CustomFunction();
	@Test(description="Toast Message")
	public void test() 
	{
		System.out.println("Inside Toast message");
		//click on dropdown
		DriverFactory.getInstance().getDriver().findElement(By.id("android:id/text1")).click();
		
//		select option from dropdown
		cf.scrollToElement("Burundi");
		DriverFactory.getInstance().getDriver().findElement(By.xpath("//android.widget.TextView[@text='Burundi']")).click();

		//Enter name in textbox
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bibhu");
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Hide Keyboard
		cf.closeKeyboard();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//click on female radio button
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();

		//click on let's shop
		DriverFactory.getInstance().getDriver().findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		//verify whether the toast message is giving the correct message
		String toastmsg=DriverFactory.getInstance().getDriver().findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastmsg, "Please enter your name");
	}
}
