package flipkartTest;

import org.testng.annotations.Test;

import pageObjects.ChooseYourLanguagePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchResultPage;
import testBase.TestBase;


public class TC01 extends TestBase
{
	HomePage hp=new HomePage();
	ChooseYourLanguagePage cyl=new ChooseYourLanguagePage();
	LoginPage lp=new LoginPage();
	SearchResultPage srp=new SearchResultPage();
	
	@Test(description="Searching Product")
	public void test() throws InterruptedException
	{
		if(cyl.checkIfChooseYourLanguagePageIsOpened())
		{
			cyl.chooseLanguage("English");
		}
		if(lp.checkIfLoginPageIsOpened())
		{
			lp.closeLoginPage();
		}
		
		hp.searchProduct("SearchBar","Mobile");
		
		if(srp.checkIfAllowLocationAccessIsPresent())
		{
			srp.allowLocationAccess();
		}
		if(srp.checkIfAllowFlipkartDevicesLocationAlertIsPresent())
		{
			srp.allowOnlyWhileUsingTheApp();
		}
		Thread.sleep(5000);
	}
	

}
