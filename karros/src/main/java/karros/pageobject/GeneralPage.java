package karros.pageobject;

import constant.Constants;

public class GeneralPage {
	
	public void gotoPage() {  
		Constants.WEBDRIVER.navigate().to(Constants.URL_Wesite);
	}
}
