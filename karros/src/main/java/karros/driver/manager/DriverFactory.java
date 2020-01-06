package karros.driver.manager;

import karros.browser.chrome.ChromeDriverManager;

public class DriverFactory {
	public static DriverManager getDriverManager(DriverType type)
	{
		DriverManager driverManager;
		switch(type)
		{
			case Chrome:
				driverManager= new ChromeDriverManager();
			case Firefox:
			case IE:
			case Edge:
			default:
				driverManager=new ChromeDriverManager();
		}
		return driverManager;
	}
}
