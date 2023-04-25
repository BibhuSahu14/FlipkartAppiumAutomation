package utils;

import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {

	private DriverFactory() {
		
	}
	
	private static DriverFactory instance  = new DriverFactory();
	
	public static DriverFactory getInstance() {
		return instance;
	}
	
	ThreadLocal<AndroidDriver> driver = new ThreadLocal<AndroidDriver>();
	
	public AndroidDriver getDriver() {
		return driver.get();
	}
	
	public void setDriver(AndroidDriver driverObj) {
		driver.set(driverObj);
	}
}
