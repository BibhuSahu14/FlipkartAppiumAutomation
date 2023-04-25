package flipkartTest;

import org.testng.annotations.Test;

import pageObjects.ChooseYourLanguagePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyCart;
import pageObjects.ProductPage;
import pageObjects.SearchResultPage;
import testBase.TestBase;

public class TC02 extends TestBase
{
	HomePage hp=new HomePage();
	ChooseYourLanguagePage cyl=new ChooseYourLanguagePage();
	LoginPage lp=new LoginPage();
	SearchResultPage srp=new SearchResultPage();
	ProductPage pp=new ProductPage();
	MyCart mc=new MyCart();
	
	@Test(description="Product add to cart")
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
		
		hp.searchProduct("SearchBar","Poco");
		
		if(srp.checkIfAllowLocationAccessIsPresent())
		{
			srp.allowLocationAccess();
		}
		if(srp.checkIfAllowFlipkartDevicesLocationAlertIsPresent())
		{
			srp.allowOnlyWhileUsingTheApp();
		}
		Thread.sleep(5000);
		srp.clickOnProduct();
		
		pp.clickOnAddToCart();
		Thread.sleep(5000);
		
		pp.clickOnGoToCart();
		
		mc.checkIfMyCartPageIsOpened();
		
		mc.checkPresentOfProductInMyCart("POCO");
		
	}
	
	
}
