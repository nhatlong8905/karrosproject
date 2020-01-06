package config;

import helper.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import report.ExtentManager;
import report.ExtentTestManager;

import com.aventstack.extentreports.ExtentTest;
import common.Utilities;

public class TestListener implements ITestListener {

	private static Map<String, ExtentTest> testSuite = new HashMap<String, ExtentTest>();
	
	public void onTestFailure(ITestResult result) {
		// Get screenshot
		String screenshotFileName = UUID.randomUUID().toString();
		String path = Utilities.captureScreenshot(screenshotFileName, ExtentTestManager.getScreenshotFolder());
		String script = Utilities.screenshotURI(path);
		Reporter.log(script);
		
		// Handle for ExtentReports
		String executionInfo = Utilities.getRemoteInfo();
		ExtentTest detailFailed = ExtentTestManager.getTest();
		if(result.getThrowable().toString().contains("Assert")) { 
			detailFailed.fail("<b>This test case check failed.</b>" + executionInfo);
			detailFailed.fail(result.getThrowable().getMessage());
		}
		else {
			detailFailed.error("<b>This test case failed by error.</b>" + executionInfo);
			detailFailed.error(result.getThrowable());
		}
	}

	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName(), testSuite.get(result.getTestContext().getName()));
		  ExtentTestManager.getTest().assignCategory(result.getTestContext().getName());
		  Logger.info(String.format("TEST CASE: %s.%s", result.getTestClass().getName(), result.getName()).replace("_", "_ "));
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTest detailInfo = ExtentTestManager.getTest();
		String executionInfo = Utilities.getRemoteInfo();
		detailInfo.pass("Test passed." + executionInfo);
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	

	public void onStart(ITestContext context) {
		if (!ExtentTestManager.isTestExisted(context.getName())) {
			ExtentTest tmpSuite = ExtentTestManager.startTest(context.getName(), null);
			testSuite.put(context.getName(), tmpSuite);
		}
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	public void onFinish(ITestContext context) {
		try {
			ExtentManager.getReporter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
