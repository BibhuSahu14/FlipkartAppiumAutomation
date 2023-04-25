package testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.DriverFactory;
import utils.Log4j;


public class TestBase 
{
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	private static AppiumDriverLocalService service;

	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}
	 public Properties getProps() {
		  return props.get();
	  }
	  
	  public void setProps(Properties props2) {
		  props.set(props2);
	  }

	@BeforeSuite(alwaysRun = true)
	public void startServer() throws MalformedURLException
	{
		System.out.println("Inside start server");
		try
		{
			Log4j.setLogger();
			service=new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\bibhuprasads\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).
				withIPAddress("127.0.0.1").withArgument(GeneralServerFlag.BASEPATH,"/wd/hub").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
//			url=service.getUrl();
			if (!checkIfAppiumServerIsRunnning(4723)) {
				service.start();
				service.clearOutPutStreams();
				Log4j.info("Appium server started");
			} else {
				Log4j.info("Appium server already running");
			}
		}
		catch(Throwable e)
		{
			Log4j.error("Failed to start Appium server");
		}
	}
//	public AppiumDriverLocalService getAppiumServerDefault() {
//		return AppiumDriverLocalService.buildDefaultService();
//	}
	private boolean checkIfAppiumServerIsRunnning(int port) {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	@Parameters({"automationName", "platformName", "platformVersion", "deviceName"})
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String automationName, String platformName, String platformVersion, String deviceName)
	{
		System.out.println("executing before test");
		setDateTime(dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		System.out.println("After device name");
		URL url;
		AndroidDriver driver;
		try {
		Properties props=new Properties();
		FileInputStream fis=new FileInputStream("src/main/resource/config.properties");
		props.load(fis);
		setProps(props);
		UiAutomator2Options options=new UiAutomator2Options();
		options.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		options.setPlatformName(platformName);
		options.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		options.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//		options.setCapability("appPackage", props.getProperty("androidAppPackage"));
//		options.setCapability("appActivity", props.getProperty("androidAppActivity"));
		options.setCapability(MobileCapabilityType.APP, props.getProperty("androidAppLocation"));
		url = new URL(props.getProperty("appiumURL"));
		driver = new AndroidDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DriverFactory.getInstance().setDriver(driver);
		}catch(Exception e)
		{
			System.out.println("driver initialization failure"+e.toString());
		}
	}
	
	public static String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	@AfterTest (alwaysRun = true)
	  public void afterTest() {
		System.out.println("Inside after test");
			  if(DriverFactory.getInstance().getDriver() != null){
				  DriverFactory.getInstance().getDriver().quit();
			  }
	  }
	@AfterSuite(alwaysRun = true)
	public void afterSuite() 
	{
		System.out.println("Inside after suite");
		if(service.isRunning())
		{
			service.stop();
		}
	}
}
