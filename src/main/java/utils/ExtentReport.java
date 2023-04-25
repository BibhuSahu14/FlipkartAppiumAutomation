package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.TestBase;

public final class ExtentReport 
{

	public static ExtentReports report=null;
	private static ExtentSparkReporter spark=null;
	public static ExtentTest test;

	public static ExtentReports initReports()
	{
		report=new ExtentReports();
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/FlipkartTest "+TestBase.dateTime()+".html");
		report.attachReporter(spark);
		report.setSystemInfo("HostName", "MindFire");
		String QAname=(System.getProperty("user.home").substring(System.getProperty("user.home").lastIndexOf("\\"))).replace("\\", "");
		report.setSystemInfo("QAname", QAname);
		report.setSystemInfo("Environment", "QA");

		return report;
	}
}
