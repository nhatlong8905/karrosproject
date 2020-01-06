package api.resources;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DemoPostsApi {

	public Response getAllProject() {
		Response res = RestAssured.
				given().
				when().
				get().andReturn();
		return res;
	}
}
