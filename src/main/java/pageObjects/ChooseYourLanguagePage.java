package pageObjects;

import org.openqa.selenium.By;

import reusableFunctions.CustomFunction;

public class ChooseYourLanguagePage 
{
	String language= "//android.widget.TextView[@text='XXXXX']";
	By continueButton=By.id("com.flipkart.android:id/select_btn");
	By chooseYourLanguageText=By.id("com.flipkart.android:id/title");
	
	CustomFunction cf=new CustomFunction();
	
	public void chooseLanguage(String choosenLanguage) throws InterruptedException
	{
		System.out.println("chooseLanguage 1");
		By languageLocator=cf.getDynamicXpath(language, choosenLanguage);
		cf.scrollToElement(choosenLanguage);
		cf.click_custom(languageLocator, choosenLanguage);
		cf.click_custom(continueButton, "Continue");
		System.out.println("chooseLanguage 2");
	}
	
	public Boolean checkIfChooseYourLanguagePageIsOpened()
	{
		boolean st=cf.isPresent(chooseYourLanguageText, "Choose your language", 10);
		System.out.println("checkIfChooseYourLanguagePageIsOpened ... "+st);
		return st;
	}
}
