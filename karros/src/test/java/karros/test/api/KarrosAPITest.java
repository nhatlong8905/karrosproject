package karros.test.api;

import helper.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.functions.DemoPostsFunc;

public class KarrosAPITest extends APITestBase {

	@Test(description = "API - Get/Check Information from Demo Posts")
	public void TC002()
	{
		DemoPostsFunc demoFunc = new DemoPostsFunc();
		Logger.info("Step 1: get the status code of API");
		int statusCode = demoFunc.getStatus();
		
		Logger.info("Step 2: check the status code of API");
		Assert.assertEquals(statusCode, 200, "Correct");
		
		Logger.info("Step 3: get the value of ID in Json file");
		String strID = demoFunc.getValueJsonObject("id");
		
		Logger.info("Step 4: check the value of ID in Json file");
		Assert.assertEquals(strID, "1", "ID Correct");
		
		Logger.info("Step 5: get the value of Title in Json file");
		String strTitle = demoFunc.getValueJsonObject("title");
		
		Logger.info("Step 6: check the value of Title in Json file");
		Assert.assertEquals(strTitle, "Post 1", "Title Correct");
	}
}
