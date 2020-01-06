package common.control.base.imp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Utilities;
import common.control.base.IBaseControl;
import constant.Constants;

public class BaseControl implements IBaseControl {
	private String locator;
	private By byLocator;
	
	public BaseControl(String locator) {
		this.locator = locator;
	}
	
	public BaseControl(By byLocator) {
		this.byLocator = byLocator;
	}
	
	protected WebDriver getWebDriver()
	{
		return Constants.WEBDRIVER;
	}
	
	protected JavascriptExecutor jsExecutor() {
		return (JavascriptExecutor) getWebDriver();
	}
	
	public WebElement getElement() {
		// TODO Auto-generated method stub
		return getWebDriver().findElement(this.byLocator);
	}

	public boolean isElement() {
		// TODO Auto-generated method stub
		return Utilities.isElement(byLocator);
	}
	public void click() {
		// TODO Auto-generated method stub
		getElement().click();
	}
	
	public List<WebElement> getElements() {
		return getWebDriver().findElements(this.byLocator);
	}
}
