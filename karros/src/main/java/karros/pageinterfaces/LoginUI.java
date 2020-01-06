package karros.pageinterfaces;

import org.openqa.selenium.By;

public class LoginUI {

	public static final By uiEmail = By.cssSelector("input[name=email]");
	public static final By uiPassword = By.cssSelector("input[name=password]");
	public static final By uiLogin = By.cssSelector("a.col-login__btn");
}
