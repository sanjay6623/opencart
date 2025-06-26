package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Import from log4j
import org.apache.logging.log4j.Logger;//Import from log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // Log4j
	public Properties p;
	public ChromeOptions options;
	

	@BeforeClass(groups = { "Sanity", "Master", "Regression", "DataDriven" })
	@Parameters({ "os", "browser" }) // Passing browser from xml file and switch
	public void setup(String os, String br) throws IOException {

		// FileReader file=new FileReader("./src//test//resources//config.properties");
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");

		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass()); // Log4j uses LogManager class and this.getClass uses for

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			// os

			if (os.equalsIgnoreCase("windows")) {

				capabilities.setPlatform(Platform.WIN11);

			}

			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);

			} else if (os.equalsIgnoreCase("Linux")) {

				capabilities.setPlatform(Platform.LINUX);
			}

			else {
				System.out.println("NO mactching so");
				return;
			}

			// browser

			switch (br.toLowerCase()) {

			case "chrome":
				capabilities.setBrowserName("chrome");
				
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		}

		// Working with local machine need to change config.properties file and enable
		// it
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			// getting class dynamically.
			switch (br.toLowerCase()) {

			case "chrome":
				
				options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				logger.info("Chrome Browser");
				break;
			case "edge":
				driver = new EdgeDriver();
				logger.info("Edge Browser");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				logger.info("Firefox Browser");
				break;
			default:
				System.out.println("Invalid browser name: ");
				return;

			}

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading url from config.properties file
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	// Random First/ Last Name, Email, Password generation
	public String randomeString() {

		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;

	}

	public String randomNumber() {

		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomAlphaNumaric() {

		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	}

	// Screenshot capturing method
	public String captureScreen(String tname) {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
