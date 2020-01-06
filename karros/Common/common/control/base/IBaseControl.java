package common.control.base;

import org.openqa.selenium.WebElement;

public interface IBaseControl {
	WebElement getElement();
	public boolean isElement();
	public void click();
}
