package pageObjects;

import org.openqa.selenium.By;

import reusableFunctions.CustomFunction;

public class LoginPage 
{
	By cross=By.id("com.flipkart.android:id/custom_back_icon");
	By LoginforthebestexperienceText=By.id("com.flipkart.android:id/title");
	
	CustomFunction cf=new CustomFunction();
	
	public void closeLoginPage()
	{
		cf.click_custom(cross, "X");
		System.out.println("closeLoginPage");
	}
	public Boolean checkIfLoginPageIsOpened()
	{
		boolean st=cf.isPresent(LoginforthebestexperienceText, "Log in for the best experience", 10);
		System.out.println("checkIfLoginPageIsOpened .... "+st);
		return st;
	}
}
