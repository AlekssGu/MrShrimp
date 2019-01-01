package shrimpbot.commands.weather;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WeatherInformationGatherer.class, FileUtils.class})
public class WeatherInformationGathererTest {

	@Mock
	FirefoxDriver firefoxDriver;

	@Mock
	WebDriver.Options webDriverOptions;

	@Mock
	WebDriver.Timeouts webDriverTimeouts;

	@Mock
	WebDriver.Window webDriverWindow;

	@Mock
	WebElement popupWebElement;

	@Mock
	File screenShot;

	@InjectMocks
	WeatherInformationGatherer weatherInformationGatherer;

	@Test
	public void getMeteoprogScreenshot() throws Exception {
		PowerMockito.whenNew(FirefoxDriver.class).withNoArguments().thenReturn(firefoxDriver);
		PowerMockito.mockStatic(FileUtils.class);

		when(firefoxDriver.manage()).thenReturn(webDriverOptions);
		when(webDriverOptions.timeouts()).thenReturn(webDriverTimeouts);
		when(webDriverOptions.window()).thenReturn(webDriverWindow);
		when(firefoxDriver.findElement(By.className(WeatherInformationGatherer.POPUP_BUTTON_CLASS_NAME))).thenReturn(popupWebElement);
		when(firefoxDriver.getScreenshotAs(OutputType.FILE)).thenReturn(screenShot);

		File meteoprogScreenshot = weatherInformationGatherer.getMeteoprogScreenshot();

		assertThat(meteoprogScreenshot).isFile();
		assertThat(meteoprogScreenshot).exists();

		verify(firefoxDriver).getScreenshotAs(OutputType.FILE);
		PowerMockito.verifyStatic(FileUtils.class);
		FileUtils.copyFile(screenShot, meteoprogScreenshot);

	}
}