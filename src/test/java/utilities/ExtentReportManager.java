  package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		/*
		 * SimpleDateFormat df=new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss"); //Date
		 * dt=new Date(); //String currentdatetimestamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkReporter.config().setDocumentTitle("opencart Automation report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Computer_Tester1", "localhost");
		extent.setSystemInfo("TesterName", "sanjay");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os"); // from project testng.xml file
		extent.setSystemInfo("Operting System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser"); // from project testng.xml file
																					// getting data dynamically
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		if (!includeGroups.isEmpty()) {

			extent.setSystemInfo("Groups", includeGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create new entry into report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "got sucessfully executed: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // create new entry into report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case FAILED: " + result.getName());
		test.log(Status.INFO, result.getThrowable());
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // create new entry into report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {
		extent.flush();
		String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathofExtentReport);

		// to open report directly open report automatically.
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {

			e.printStackTrace();
		}

		/*
		 * try { URL url=new
		 * URL("file:////"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * ImageHtmlEmail email=new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465); email.setAuthenticator(new
		 * DefaultAuthenticator("sanjay6623@gmail.com","password"));
		 * email.setSSLOnConnect(true); email.setFrom("sanjay6623@gmail.com"); //sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached report:");
		 * email.addTo("sanjay6623@gmail.com"); //Receiver
		 * email.attach(url,"extent report", "please chekc report..."); email.send(); }
		 * 
		 * catch (MalformedURLException | EmailException e) {
		 * 
		 * e.printStackTrace(); }
		 */

	}

}
