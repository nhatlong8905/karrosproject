package karros.browser.chrome;

import org.openqa.selenium.chrome.ChromeDriver;

import common.Utilities;
import karros.driver.manager.DriverManager;

public class ChromeDriverManager extends DriverManager {

	@Override
	protected void createWebDriver() {
		// TODO Auto-generated method stub
		String path = Utilities.getProjectPath() +  "\\Executables\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		this.driver=new ChromeDriver();
	}

}
