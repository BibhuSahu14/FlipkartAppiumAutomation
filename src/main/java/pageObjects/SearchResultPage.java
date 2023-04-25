package pageObjects;

import org.openqa.selenium.By;

import reusableFunctions.CustomFunction;

public class SearchResultPage 
{
	By allowLocationAccessText=By.id("com.flipkart.android:id/permission_title");
	By continue_allowButton=By.id("com.flipkart.android:id/allow_button");
	By not_NowButton=By.id("com.flipkart.android:id/not_now_button");
	By allowFlipkartDevicesLocationText=By.id("com.android.permissioncontroller:id/permission_message");
	By allowOnlyWhileUsingTheApp_Button=By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
	By deny_Button=By.id("com.android.permissioncontroller:id/permission_deny_button");
	By product=By.xpath("//android.widget.TextView[contains(@text,'POCO') and contains(@text,'64')]");
	By pagescrollarea=By.xpath("(//android.view.ViewGroup)[1]");
	
	CustomFunction cf=new CustomFunction();
	
	public Boolean checkIfAllowLocationAccessIsPresent()
	{
		boolean st=cf.isPresent(allowLocationAccessText, "Allow Location Access", 10);
		System.out.println("checkIfAllowLocationAccessIsPresent..."+st);
		return st;
	}
	
	public void allowLocationAccess()
	{
		cf.click_custom(continue_allowButton, "CONTINUE");
	}
	
	public void donotAllowLocationAccess()
	{
		cf.click_custom(not_NowButton, "NOT NOW");
	}
	public Boolean checkIfAllowFlipkartDevicesLocationAlertIsPresent()
	{
		boolean st=cf.isPresent(allowFlipkartDevicesLocationText, "Allow Flipkart to access this device's location?", 10);
		System.out.println("checkIfAllowFlipkartDevicesLocationAlertIsPresent..."+st);
		return st;
	}
	public void allowOnlyWhileUsingTheApp()
	{
		cf.click_custom(allowOnlyWhileUsingTheApp_Button, "Allow only while using the app");
	}
	public void denyLocationPermission()
	{
		cf.click_custom(deny_Button, "Deny");
	}
	public void clickOnProduct()
	{
		try {
			cf.verticalScrollDownToElementUsingAction(pagescrollarea, product, "Poco");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//cf.verticalScrollUsingJavaScriptExecutor(product);
		cf.click_custom(product, "POCO");
	}
}
