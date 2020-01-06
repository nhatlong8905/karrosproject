package karros.test.api;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import api.BaseResource;

public class APITestBase {

	@BeforeMethod(alwaysRun = true)
	public void init()
			throws Throwable {
		// Do not change this function
		BaseResource.init();
	}
	@AfterMethod(alwaysRun = true)
	public void cleanUp() {
	}
}
