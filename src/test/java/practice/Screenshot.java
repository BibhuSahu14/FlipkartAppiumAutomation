package practice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;
import testBase.TestBase;
import utils.DriverFactory;

public class Screenshot extends TestBase 
{
//		E:\\APK files\\APKFiles-1\\resources\\General-Store.apk


	@Test(description="*****")
	public void test() throws InterruptedException, IOException
	{

		WebElement ele=DriverFactory.getInstance().getDriver().findElement(By.id("android:id/text1"));
		
		Thread.sleep(5000);

		//fullScreenshot(driver);
		
		elementScreenshot(DriverFactory.getInstance().getDriver(),ele);
	}

	public static void fullScreenshot(AndroidDriver driver) throws IOException
	{
		File srcfile=driver.getScreenshotAs(OutputType.FILE);
		String path="screenshots/"+UUID.randomUUID()+".png";

		File screenshotlocation=new File(System.getProperty("user.dir")+"/"+path);

		FileUtils.copyFile(srcfile, screenshotlocation);

	}
	public void elementScreenshot(AndroidDriver driver, WebElement ele) throws IOException
	{
		File srcfile=driver.getScreenshotAs(OutputType.FILE);
		BufferedImage fulImage=ImageIO.read(srcfile);

		Point point=ele.getLocation();

		int elewidth=ele.getSize().getWidth();
		int eleheight=ele.getSize().getHeight();

		BufferedImage eleScreenshot=fulImage.getSubimage(point.getX(), point.getY(), elewidth, eleheight);
		ImageIO.write(eleScreenshot, "png", srcfile);
		String path="screenshots/"+UUID.randomUUID()+".png";

		File screenshotlocation=new File(System.getProperty("user.dir")+"/"+path);

		FileUtils.copyFile(srcfile, screenshotlocation);
	}
}
