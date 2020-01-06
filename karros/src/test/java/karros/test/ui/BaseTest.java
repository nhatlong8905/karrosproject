package karros.test.ui;

import karros.driver.manager.DriverFactory;
import karros.driver.manager.DriverType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import constant.Constants;

public class BaseTest {
	
    @BeforeMethod
	public void beforeMethod() {
		System.out.println("Run beforeMethod");
		
		Constants.driverManager=DriverFactory.getDriverManager(DriverType.Chrome);
		Constants.WEBDRIVER=Constants.driverManager.getWebDriver();
		Constants.WEBDRIVER.manage().window().maximize();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		Constants.WEBDRIVER.quit();
	}
}
