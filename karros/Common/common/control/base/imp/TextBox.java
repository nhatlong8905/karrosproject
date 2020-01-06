package common.control.base.imp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.Utilities;
import common.control.base.IEditable;

public class TextBox extends BaseControl implements IEditable {

	public TextBox(String locator) {
		super(locator);
	}
	
	public TextBox(By byLocator) {
		super(byLocator);
	}
	public void enter(CharSequence... value) {
		// TODO Auto-generated method stub
		WebElement element = getElement();
		if(Utilities.haveElementDisplay(element))
		{
			Utilities.waitForElementClickAble(element);
			element.sendKeys(value);
		}
	}

	public void setText(String value) {
		// TODO Auto-generated method stub
		
	}

	public void setValue(String value) {
		// TODO Auto-generated method stub
		try {
			String js = String.format("arguments[0].value='%s';", value);
			//logger.debug(String.format("Set value '%s' for %s", value, getLocator().toString()));
			jsExecutor().executeScript(js, getElement());
		} catch (Exception e) {
			//logger.error(String.format("Has error with control '%s': %s", getLocator().toString(), e.getMessage()));
			//throw e;
		}
	}
}
