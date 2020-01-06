package constant;

import karros.driver.manager.DriverManager;

import org.openqa.selenium.WebDriver;

public class Constants {

	public static WebDriver WEBDRIVER;
	public static DriverManager driverManager;
	public static final long SLEEP_TIME = 1;
	public static final long LONG_TIME = 30;
	public static final long SHORT_TIME = 5;
	public static String URL_Wesite;
	public static final String linkJson = "src/test/resources/data/account.json";
}
