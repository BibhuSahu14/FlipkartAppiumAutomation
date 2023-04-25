package pageObjects;

import org.openqa.selenium.By;

import reusableFunctions.CustomFunction;

public class ProductPage 
{
	By addToCartButton=By.xpath("//android.widget.TextView[@text='Add to cart']");
	By goToCartButton=By.xpath("//android.widget.TextView[@text='GO TO CART']");
	
	CustomFunction cf=new CustomFunction();
	
	public void clickOnAddToCart()
	{
		cf.click_custom(addToCartButton,"Add to cart" );
	}
	public void clickOnGoToCart()
	{
		cf.click_custom(goToCartButton, "GO TO CART");
	}
}
