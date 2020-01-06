package api;

import helper.PropertiesHelper;
import io.restassured.RestAssured;


public class BaseResource {
		public static void init() {
		RestAssured.baseURI = PropertiesHelper.getPropValue("profile.api.host");
	}
}
