package common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import constant.Constants;

public class Utilities {
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	public static JavascriptExecutor jsExecutor() {
		return (JavascriptExecutor) Constants.WEBDRIVER;
	}
	public static void waitForElementClickAble(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(Constants.WEBDRIVER,10);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static boolean haveElementDisplay(By locator)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(Constants.WEBDRIVER,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	 
	public static boolean haveElementDisplay(WebElement element)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(Constants.WEBDRIVER,10);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean haveElementInvisible(By locator)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(Constants.WEBDRIVER,10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static void delay(double timeInSecond) {
		try {
			Thread.sleep((long)(timeInSecond * 1000));
		} catch (Exception e) {
			
		}
	}
	
	public static boolean isPageLoad(long timeout) {
		delay(2);
		JavascriptExecutor js = (JavascriptExecutor)Constants.WEBDRIVER;
		String jsStatus = "Begin";		
		long loop = timeout;
		while (loop > 0 && !jsStatus.equals("complete"))
		{	
			try {
				loop = loop - Constants.SLEEP_TIME;
				jsStatus = js.executeScript("return document.readyState").toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				jsStatus = e.getMessage();
				return false;
			}
		}
		if(jsStatus.equals("complete"))
		{
			return true;
		}
		return true;
	}

	public static boolean isPageLoad()
	{
		return isPageLoad(Constants.SHORT_TIME);
	}
	
	public static boolean isAttribtuePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
	public static boolean isElement(By element)
	{
		return Constants.WEBDRIVER.findElements(element).size() != 0;
	}
	
	public static void setValue(WebElement element,String value) {
		try {
			String js = String.format("arguments[0].value='%s';", value);
			jsExecutor().executeScript(js, element);
		} catch (Exception e) {
			
		}
	}

	public static JSONArray readFileJson(String link) {
		
		JSONParser parser = new JSONParser();
		JSONArray listObjectJson = null;
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get(link)));
			Object obj = parser.parse(fileContent);		
			listObjectJson = (JSONArray) obj;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listObjectJson;
	}
	
	public static ArrayList<String> getInforAcc(String link)
	{
		JSONArray listObjectJson=readFileJson(link);
		ArrayList<String> listJson = new ArrayList<String>();
		for(int i=0;i<listObjectJson.size();i++)
		{
			JSONObject dataObject = (JSONObject) listObjectJson.get(i);
			String userName=getStringFromJSON(dataObject,"username");
			listJson.add(userName);
			String passWord=getStringFromJSON(dataObject,"password");
			listJson.add(passWord);
			String website=getStringFromJSON(dataObject,"website");
			listJson.add(website);
		}
		return listJson;
	}
	
	public static String getStringFromJSON(JSONObject data, String key) {
		if(data == null)
			return "";
		return String.valueOf(data.get(key));
	}
	
	public static String getNowTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getRemoteInfo() {
		StringBuilder str = new StringBuilder();
		try {
			CommandExecutor ce = ((RemoteWebDriver) Constants.WEBDRIVER).getCommandExecutor();
			URL url = ((HttpCommandExecutor) ce).getAddressOfRemoteServer();

			InetAddress host = InetAddress.getByName(url.getHost());
			Capabilities caps = ((RemoteWebDriver) Constants.WEBDRIVER).getCapabilities();

			str.append("<br>Execution Information:");
			str.append("<br>- Machine IP: <b>" + host.getHostAddress() + " - " + host.getHostName() + "</b>");
			str.append("<br>- Browser: <b>" + caps.getBrowserName().toUpperCase());
			str.append(" - " + caps.getVersion() + "</b>");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	public static String captureScreenshot(String filename, String filepath) {
		String path = "";
		try {
			// Taking the screen using TakesScreenshot Class
			File objScreenCaptureFile = ((TakesScreenshot) Constants.WEBDRIVER).getScreenshotAs(OutputType.FILE);

			// Storing the image in the local system.
			File dest = new File(
					System.getProperty("user.dir") + File.separator + filepath + File.separator + filename + ".png");
			FileUtils.copyFile(objScreenCaptureFile, dest);
			path = dest.getAbsolutePath();
		} catch (Exception e) {
		}
		return path;
	}
	
	public static String screenshotURI(String imagePath) {
		String randomPopUpId = "id" + UUID.randomUUID().toString();
		String randomButtonId = randomPopUpId + "button";
		String imageString = "data:image/png;base64," + convertImageToURI(imagePath);
		String htmlScript = "<script>$(document).ready(function(){$( \"#" + randomPopUpId
				+ "\" ).dialog({ autoOpen: false });$( \"#" + randomPopUpId
				+ "\" ).dialog({width:1000},{height:700});$( \"#" + randomButtonId + "\" ).click(function() {$( \"#"
				+ randomPopUpId + "\" ).dialog( \"open\" );});});</script></br><img id=\"" + randomButtonId
				+ "\" src=\"" + imageString
				+ "\" style=\"border: 4px solid #f6f7fa;width: 150px;cursor: zoom-in;display: block;margin-top: 15px;\"/></br><div style=\"width: 50%; margin: 0 auto;\" id=\""
				+ randomPopUpId + "\" > <a href=\"#" + randomPopUpId
				+ "\"  class=\"ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right\"></a><img style=\"width:800px;height:600;\"  src=\""
				+ imageString + "\"/></div>";
		return htmlScript;
	}
	
	public static String convertImageToURI(String imagePath) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BufferedImage img;
		File image = new File(imagePath);
		try {
			img = ImageIO.read(image);
			ByteArrayOutputStream convert = new ByteArrayOutputStream();
			ImageIO.write(img, "png", convert);
			String data = DatatypeConverter.printBase64Binary(convert.toByteArray());
			return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
