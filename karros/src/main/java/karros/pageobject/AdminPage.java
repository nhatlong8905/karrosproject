package karros.pageobject;

import java.util.List;

import karros.pageinterfaces.AdminPageUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.Utilities;
import common.control.base.imp.BaseControl;


public class AdminPage extends GeneralPage{

	public int countRequest()
	{
		Utilities.haveElementDisplay(AdminPageUI.listRowsUI);
		int count=0;
		BaseControl rows = new BaseControl(AdminPageUI.listRowsUI);
		List<WebElement> listRows = rows.getElements(); 
		for ( int i=0;i<listRows.size();i++)
		{
			WebElement iRows = listRows.get(i);
			boolean tRow= detechValueFromRow(iRows.findElements(By.tagName("td")));
			if(tRow)
			{
				count ++;
			}
		}
		return count;
	}
	
	public boolean detechValueFromRow (List<WebElement> listColumn)
	{
		boolean approved= false;
		for(int i=0;i< listColumn.size();i++)
		{
			String item = listColumn.get(i).getText();
			if(item.equals("Approved"))
			{
				approved = true;
			}
			if(approved && item.contains("2019"))
			{
				approved = false;
				return true;
			}
		}
		return false;
	}
}
