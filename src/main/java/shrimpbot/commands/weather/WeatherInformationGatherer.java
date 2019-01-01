package shrimpbot.commands.weather;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WeatherInformationGatherer {

	static final String METEOPROG_WEBSITE_URL = "http://www.meteoprog.lv/lv/";
	static final String POPUP_BUTTON_CLASS_NAME = "gdpr-dialog__btn";

	File getMeteoprogScreenshot() {
		final File screenShot = new File("weather.png").getAbsoluteFile();

		prepareScreenAndGetScreenShot(screenShot);

		return screenShot;
	}

	private void prepareScreenAndGetScreenShot(File screenShot) {
		final WebDriver driver = new FirefoxDriver();
		try {
			prepareScreenForScreenshot(driver);
			final File outputFile = takeScreenShot(driver);
			fillScreenShotVariable(screenShot, outputFile);
		} finally {
			driver.close();
		}
	}

	private File takeScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	private void prepareScreenForScreenshot(WebDriver driver) {
		final String link = METEOPROG_WEBSITE_URL;
		driver.get(link);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(1024, 728));
		driver.findElement(By.className(POPUP_BUTTON_CLASS_NAME)).click();
	}

	private void fillScreenShotVariable(File screenShot, File outputFile) {
		try {
			FileUtils.copyFile(outputFile, screenShot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
