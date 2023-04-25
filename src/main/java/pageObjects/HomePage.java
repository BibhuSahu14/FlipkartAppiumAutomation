package pageObjects;

import org.openqa.selenium.By;
import com.google.common.collect.ImmutableMap;

import reusableFunctions.CustomFunction;
import utils.DriverFactory;

public class HomePage 
{
	By categoryHorizontalScroll=By.xpath("//android.widget.HorizontalScrollView");
	
	By searchBar=By.xpath("//android.widget.TextView[@text='Search for Products, Brands and More']");
	
	By Searchgroceryproducts=By.xpath("//android.widget.EditText[@resource-id='com.flipkart.android:id/search_autoCompleteTextView']");
	
	
	CustomFunction cf=new CustomFunction();
	
	public void searchProduct(String fieldName, String productName)
	{
		cf.click_custom(searchBar, fieldName);
		cf.sendKeys_custom(Searchgroceryproducts, fieldName, productName);
		//DriverFactory.getInstance().getDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
		DriverFactory.getInstance().getDriver().executeScript("mobile:performEditorAction", ImmutableMap.of("action", "Search"));
	}
	
}
