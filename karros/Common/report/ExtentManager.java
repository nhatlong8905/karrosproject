package report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter(String outputResult) throws Exception {
		if (extent == null) {
			File output = new File(outputResult);
			new File(output.getParent()).mkdir();
			
			if (outputResult == null || outputResult.trim().length() == 0)
				throw new Exception("Output folder result is required");
			ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(outputResult);
			htmlReport.setAppendExisting(true);
			// htmlReport.loadXMLConfig("config.xml");
			extent = new ExtentReports();
			extent.attachReporter(htmlReport);
		}
		return extent;
	}

	public synchronized static ExtentReports getReporter() throws Exception {
		try {
			return getReporter("");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static boolean isReportCreated() {
		return (extent != null) ? true : false;
	}
}
