package karros.pageobject;

import karros.pageinterfaces.LoginUI;
import common.control.base.imp.Button;
import common.control.base.imp.TextBox;

public class LoginPage extends GeneralPage {
	
	public AdminPage login(String username, String password) {
		TextBox txtUserName = new TextBox(LoginUI.uiEmail); 
		TextBox txtPassword = new TextBox(LoginUI.uiPassword);	
		Button btnLogin = new Button(LoginUI.uiLogin);
		
		txtUserName.enter(username);
		txtPassword.enter(password);
		btnLogin.click();
		
		return new AdminPage();
	}
	
}
