package helper;

import java.util.ArrayList;
import java.util.List;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import report.ExtentTestManager;



public class Logger {
	private static String methodName;
	private static String clasName;
	private static List<String> currentLogs = new ArrayList<String>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(Logger.class);

	public static void info(String message) {
		Reporter.log("<b>INFO: </b>" + message);
		saveLog(message);
		log.info(message);
		ExtentTestManager.getTest().log(Status.INFO, "<span style=word-break:keep-all>" + message + "</span>");
	}

	public static void bug(String bugId, String bugLink) {
		String bugInfo = String.format(
				"The bug %s is added", bugId);
		String msg = "<a target=\"_blank\" href=\"" + bugLink
				+ "\" style=\"color:#DF0101;font-size:14px;word-break:keep-all;\">" + bugInfo
				+ "</a>";
		Reporter.log(msg);
		log.error(msg);
		saveLog(msg);
		ExtentTestManager.getTest().log(Status.WARNING, msg);
	}
	
	public static void verify(String message) {
		Reporter.log(message);
		log.info("VERIFY POINT: " + message);
		saveLog("VERIFY POINT: " + message);
		message = "<i><b >VERIFY POINT: </b></i>"+ message;
		ExtentTestManager.getTest().log(Status.INFO, "<b> " + message + "</b>");
	}
	
	private static void saveLog(String message) {
		String currentMethod = Thread.currentThread().getStackTrace()[3]
				.getMethodName();
		String currentClass = Thread.currentThread().getStackTrace()[3]
				.getClassName();
		if (!currentMethod.equals(methodName)
				|| (currentMethod.equals(methodName) && !currentClass
						.equals(clasName))) {
			currentLogs.clear();
		}
		methodName = currentMethod;
		clasName = currentClass;
		currentLogs.add(message);
	}
	
	public static List<String> getCurrentLogs() {
		return currentLogs;
	}
}
