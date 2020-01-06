package karros.test.ui;

import helper.Logger;

import java.util.ArrayList;

import karros.pageobject.AdminPage;
import karros.pageobject.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Utilities;
import constant.Constants;
import data.object.Account;

public class KarrosTest extends BaseTest {

	public Account accTest;
	public void precodition()
	{
		Logger.info("Pre-conditon: get infor account");
		ArrayList<String>  infoAccount = Utilities.getInforAcc(Constants.linkJson);
		
		accTest = new Account();
		accTest.setUsername(infoAccount.get(0));
		accTest.setPassword(infoAccount.get(1));
		accTest.setWebsite(infoAccount.get(2));
		Constants.URL_Wesite = infoAccount.get(2);
	}
	
	@Test(description = "Get number request")
	public void TC001()
	{
		Logger.info("TC001: User Admin get the number of request with Request Status = Approved and Data Submitted = 2019");
		precodition();
		
		Logger.info("Step 1: Login to Web");
		LoginPage login = new LoginPage();
		login.gotoPage();
		
		AdminPage admin = login.login(accTest.getUsername(),accTest.getPassword());
		
		Logger.info("Step 2: Get number request with condition Request Status = Approved and Data Submitted = 2019");
		int numberRequest = admin.countRequest();
		
		Logger.info("Step 3: Check pass number request with condition Request Status = Approved and Data Submitted = 2019");
		Assert.assertEquals(numberRequest, 5);
		
		Logger.info("Step 4: Check fail number request with condition Request Status = Approved and Data Submitted = 2019");
		Assert.assertEquals(numberRequest, 8, "fail");
		
		
	}
}
