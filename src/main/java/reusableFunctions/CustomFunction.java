package reusableFunctions;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import utils.DriverFactory;
import utils.ExtentFactory;
import utils.Log4j;

public class CustomFunction 
{
	///////////////////////
	static public PointerInput pointer;
	static public PointerInput pointer2;
	static public Interaction touch ;
	static public Interaction release;
	static public Interaction moveCenter;
	static public Interaction verticalUpPoint;
	static public Interaction verticalDownPoint;
	static public Interaction horizontalLeftPoint;
	static public Interaction horizontalRightPoint;
	////////////////////

	public By getDynamicXpath(String xpathValue, String Value ) 
	{
		return By.xpath(xpathValue.replace("XXXXX", Value));
	}
	public void sendKeys_custom(By locator, String fieldName, String valueToBeSent) {
		try {
			waitForVisibility(locator, 10);
			WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
			element.sendKeys(valueToBeSent);
			//log success message in extent report
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, fieldName+"==> Ented value as: "+valueToBeSent);
			Log4j.info(fieldName+"==> Ented value as: "+valueToBeSent);
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
			Log4j.error("Value enter in field: "+fieldName + " is failed due to exception: "+e);
		}
	}

	public void click_custom(By locator, String fieldName) {
		try {
			waitForVisibility(locator, 10);
			WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
			element.click();
			//log success message in extent report
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, fieldName+"==> Clicked Successfully! ");
			Log4j.info(fieldName+"==> Clicked Successfully! ");
		} catch (Exception e) {
			//log failure in extent
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to click on field: " +fieldName +" due to exception: "+e);
			Log4j.error("Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}

	public void waitForVisibility(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}




	//vertical scroll until view an element
	public void scrollToElement(String element)
	{
		try
		{
			DriverFactory.getInstance().getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+element+"\"));"));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO,"Scroll to"+ element+" Successfully! ");
			Log4j.info("Scroll to"+ element+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to scroll to : " +element +" due to exception: "+e);
			Log4j.error("Unable to scroll to : " +element +" due to exception: "+e);
		}
	}
	public void closeKeyboard()
	{
		DriverFactory.getInstance().getDriver().hideKeyboard();
	}

	public void horizontalScrollUsingJavaScriptExecutor(By locator)
	{
		WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
		Boolean canScrollMore;
		do {
			canScrollMore=(Boolean)((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("mobile: scrollGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
					"direction", "right",
					"percent", 1.0
					));
		}
		while(canScrollMore);
	}
	public void verticalScrollUsingJavaScriptExecutor(By locator)
	{
		WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
		Boolean canScrollMore;
		do {
			canScrollMore=(Boolean)((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("mobile: scrollGesture", ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
					"direction", "down",
					"percent", 1.0
					));
		}
		while(canScrollMore);
	}
	
	public void horizontalSwipeusingJavaScriptExecutor(By locator)
	{
		WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
		((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"direction", "left",
				"percent", 0.75
				));
	}

	public void verticalSwipeusingJavaScriptExecutor(By locator)
	{
		WebElement element=DriverFactory.getInstance().getDriver().findElement(locator);
		((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
				"direction", "up",
				"percent", 0.75
				));

	}

	/////////////////////////////////////////

	public void horizontalScrollLeftToElementUsingAction(By actionAreaLocator, By elementLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			while(!isPresent(elementLocator, elementName, 500))
			{
				Sequence scrollDown = new Sequence(pointer, 0);
				scrollDown.addAction(horizontalLeftPoint).addAction(touch).addAction(horizontalRightPoint).addAction(release);
				DriverFactory.getInstance().getDriver().perform(Arrays.asList(scrollDown));

			} 
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, elementName+"==> Scroll Successfully! ");
			Log4j.info("Scroll to "+ elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to scroll to : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to scroll to : " +elementName +" due to exception: "+e);
		}
	}

	public void horizontalScrollRightToElementUsingAction(By actionAreaLocator, By elementLocator, String elementName) {
		try {
			initlializeTouchObjects(actionAreaLocator);
			while(!isPresent(elementLocator, elementName, 500))
			{
				Sequence scrollUp = new Sequence(pointer, 0);
				scrollUp.addAction(horizontalRightPoint).addAction(touch).addAction(horizontalLeftPoint).addAction(release);
				DriverFactory.getInstance().getDriver().perform(Arrays.asList(scrollUp));
			} 
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Scroll to "+elementName+" Successfully! ");
			Log4j.info("Scroll to"+ elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to scroll to : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to scroll to : " +elementName +" due to exception: "+e);
		}
	}
	public void verticalScrollDownToElementUsingAction(By actionAreaLocator, By elementLocator, String elementName) throws InterruptedException {//correct
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			while(!isPresent(elementLocator, elementName, 500))
			{
				Sequence scrollDown = new Sequence(pointer, 0);
				scrollDown.addAction(verticalDownPoint).addAction(touch).addAction(verticalUpPoint).addAction(release);
				DriverFactory.getInstance().getDriver().perform(Arrays.asList(scrollDown));

			} 
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Scroll to"+ elementName+" Successfully! ");
			Log4j.info("Scroll to"+ elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to scroll to : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to scroll to : " +elementName +" due to exception: "+e);
		}
	}

	public void verticalScrollUpToElementUsingAction(By actionAreaLocator, By elementLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			while(!isPresent(elementLocator, elementName, 500))
			{
				Sequence scrollUp = new Sequence(pointer, 0);
				scrollUp.addAction(verticalUpPoint).addAction(touch).addAction(verticalDownPoint).addAction(release);
				DriverFactory.getInstance().getDriver().perform(Arrays.asList(scrollUp));
			} 
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Scroll to"+ elementName+" Successfully! ");
			Log4j.info("Scroll to"+ elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to scroll to : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to scroll to : " +elementName +" due to exception: "+e);
		}
	}

	public void swipeDown(By actionAreaLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence swipe = new Sequence(pointer, 0);
			swipe.addAction(verticalDownPoint).addAction(touch).addAction(verticalUpPoint).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(swipe));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Swipe to "+elementName+" Successfully! ");
			Log4j.info("Swipe to "+elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to swipe : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to swipe : " +elementName +" due to exception: "+e);
		}
	}

	public void swipeUp(By actionAreaLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence swipe = new Sequence(pointer, 0);
			swipe.addAction(verticalUpPoint).addAction(touch).addAction(verticalDownPoint).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(swipe));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Swipe to "+elementName+" Successfully! ");
			Log4j.info("Swipe to "+elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to swipe : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to swipe : " +elementName +" due to exception: "+e);
		}
	}
	public void swipeLeft(By actionAreaLocator, String elementName) throws InterruptedException {
		try 
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence swipe = new Sequence(pointer, 0);
			swipe.addAction(horizontalLeftPoint).addAction(touch).addAction(horizontalRightPoint).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(swipe));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Swipe to "+elementName+" Successfully! ");
			Log4j.info("Swipe to "+elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to swipe : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to swipe : " +elementName +" due to exception: "+e);
		}
	}

	public void swipeRight(By actionAreaLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence swipe = new Sequence(pointer, 0);
			swipe.addAction(horizontalRightPoint).addAction(touch).addAction(horizontalLeftPoint).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(swipe));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Swipe to "+elementName+" Successfully! ");
			Log4j.info("Swipe to "+elementName+" Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to swipe : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to swipe : " +elementName +" due to exception: "+e);
		}
	}

	public void pinchZoomIn(By actionAreaLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence zoomInPointerOne = new Sequence(pointer, 0);
			Sequence zoomInPointerTwo = new Sequence(pointer2, 0);
			zoomInPointerOne.addAction(moveCenter).addAction(touch).addAction(verticalUpPoint).addAction(release);
			zoomInPointerTwo.addAction(moveCenter).addAction(touch).addAction(verticalDownPoint).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(zoomInPointerOne, zoomInPointerTwo));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, elementName+"==> Zooming in Successfully! ");
			Log4j.info(elementName+"==> Zooming in Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to zoom in : " +elementName +" due to exception: "+e);
			Log4j.error( "Unable to zoom in : " +elementName +" due to exception: "+e);
		}
	}

	public  void pinchZoomOut(By actionAreaLocator, String elementName) throws InterruptedException {
		try
		{
			initlializeTouchObjects(actionAreaLocator);
			Sequence zoomOutPointerOne = new Sequence(pointer, 0);
			Sequence zoomOutPointerTwo = new Sequence(pointer2, 0);
			zoomOutPointerOne.addAction(verticalUpPoint).addAction(touch).addAction(moveCenter).addAction(release);
			zoomOutPointerTwo.addAction(verticalDownPoint).addAction(touch).addAction(moveCenter).addAction(release);
			DriverFactory.getInstance().getDriver().perform(Arrays.asList(zoomOutPointerOne, zoomOutPointerTwo));
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, elementName+"==> Zooming out Successfully! ");
			Log4j.info(elementName+"==> Zooming out Successfully! ");
		}
		catch(Exception e)
		{
			ExtentFactory.getInstance().getExtentTest().log(Status.INFO, "Unable to zoom out : " +elementName +" due to exception: "+e);
			Log4j.error("Unable to zoom out : " +elementName +" due to exception: "+e);
		}
	}

	public void initlializeTouchObjects(By actionAreaLocator) {
		pointer = new PointerInput(Kind.TOUCH, "finger");
		pointer2 = new PointerInput(Kind.TOUCH, "finger2");
		touch = pointer.createPointerDown(0);
		release = pointer.createPointerUp(0);
		WebElement deviceActionArea=DriverFactory.getInstance().getDriver().findElement(actionAreaLocator);
		///
		int centerX=deviceActionArea.getRect().x + (deviceActionArea.getSize().width/2);
		int centerY=deviceActionArea.getRect().y + (deviceActionArea.getSize().height/2);
		int startY=(int) (deviceActionArea.getRect().y + (deviceActionArea.getSize().height*0.9));
		int endY=(int) (deviceActionArea.getRect().y + (deviceActionArea.getSize().height*0.1));
		int startX=(int) (deviceActionArea.getRect().x +(deviceActionArea.getSize().width*0.9));
		int endX=(int) (deviceActionArea.getRect().x + (deviceActionArea  .getSize().width*0.1));

		///
		moveCenter = pointer.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, centerY);
		verticalUpPoint=pointer.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY);
		verticalDownPoint=pointer.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, startY);
		horizontalRightPoint=pointer.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, centerY);
		horizontalLeftPoint=pointer.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY);
	}

	public boolean isPresent(By locator, String elementName, int timeoutInMilliSeconds) {
		//		waitForVisibility(locator, 10);
		WebDriverWait wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofMillis(timeoutInMilliSeconds));
		try { 
			DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return false;
		} catch (TimeoutException e) {
			return true;
		}
		finally
		{
			DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	}

}
