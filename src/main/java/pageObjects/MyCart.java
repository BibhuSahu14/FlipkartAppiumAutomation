package pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import reusableFunctions.CustomFunction;

public class MyCart 
{
	By myCartText=By.id("com.flipkart.android:id/title_action_bar");
	String dynamicProductName="//android.widget.TextView[contains(@text,'XXXXX')]";
	
	CustomFunction cf=new CustomFunction();
	public void checkIfMyCartPageIsOpened()
	{
		try
		{
			boolean st=cf.isPresent(myCartText, "My Cart", 10);
			Assert.assertEquals(true, st);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void checkPresentOfProductInMyCart(String product)
	{
		By locator=cf.getDynamicXpath(dynamicProductName, product);
		Assert.assertEquals(true, cf.isPresent(locator, product, 10));
	}
}
