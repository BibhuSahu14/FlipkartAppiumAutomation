package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	//This method captures screenshot
public static void captureScreenshot(WebDriver driver, String testStepName) throws IOException{
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH_mm_ss");
		String formatedDateAndTime = now.format(format);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString() +testStepName+".png"));
		
	}
	
public static String captureScreenshotReturnPath(WebDriver driver, String testStepName) throws IOException{
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH_mm_ss");
		String formatedDateAndTime = now.format(format1);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString() +testStepName+".png"));
		return System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString() +testStepName+".png";
	}
	
	//================================================>
	
	public static void addurlToScreenShot(WebDriver driver, String url) throws IOException{
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH_mm_ss");
		String formatedDateAndTime = now.format(format);
		
		
	    final BufferedImage image = ImageIO.read(src);
	    Graphics g = image.getGraphics();
	    g.setFont(g.getFont().deriveFont(20f));
	    g.setColor(Color.BLACK);
	    g.drawString(url, 25, 25);
	    g.dispose();

	    ImageIO.write(image, "png", new File(System.getProperty("user.dir")+"\\Screenshots\\" + formatedDateAndTime.toString() +"Capture.png"));
	}
}
