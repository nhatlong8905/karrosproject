package api.functions;

import api.resources.DemoPostsApi;
import helper.Logger;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DemoPostsFunc {

	DemoPostsApi apiDemoPosts;
	Response res;
	public DemoPostsFunc()
	{
		apiDemoPosts = new DemoPostsApi();
		res = apiDemoPosts.getAllProject();
	}
	
	public int getStatus()
	{
		return res.getStatusCode();
	}
	
	public String getValueJsonObject(String name)
	{
		if (res.statusCode() != 200)
		{
			Logger.info("Failed to search Issue. Status code: " + res.statusCode());
			return "";
		}
		JsonPath jsBody = res.jsonPath();
		String strObject= String.valueOf(jsBody.get(name));
		return strObject;
	}
	
}
