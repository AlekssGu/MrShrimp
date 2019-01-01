package shrimpbot.commands.motivate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static shrimpbot.commands.motivate.MotivationQuoteGatherer.BTN_GENERATE_CLASS_NAME;
import static shrimpbot.commands.motivate.MotivationQuoteGatherer.GENERATED_IMAGE_CLASS_NAME;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MotivationQuoteGatherer.class)
public class MotivationQuoteGathererTest {

	private static final String IMAGE_SOURCE_URL = "https://somerandomurl.com";

	@Mock
	FirefoxDriver firefoxDriver;

	@Mock
	WebDriver.Options webDriverOptions;

	@Mock
	WebDriver.Timeouts webDriverTimeouts;

	@Mock
	WebDriver.Window webDriverWindow;

	@Mock
	WebElement generateButtonElement;

	@Mock
	WebElement generatedImageElement;

	@InjectMocks
	private MotivationQuoteGatherer motivationQuoteGatherer;

	@Test
	public void getMotivationalQuotePhotoUrl() throws Exception {
		PowerMockito.whenNew(FirefoxDriver.class).withNoArguments().thenReturn(firefoxDriver);

		when(firefoxDriver.manage()).thenReturn(webDriverOptions);
		when(webDriverOptions.timeouts()).thenReturn(webDriverTimeouts);
		when(webDriverOptions.window()).thenReturn(webDriverWindow);
		when(firefoxDriver.findElement(By.className(BTN_GENERATE_CLASS_NAME))).thenReturn(generateButtonElement);
		when(firefoxDriver.findElement(By.className(GENERATED_IMAGE_CLASS_NAME))).thenReturn(generatedImageElement);
		when(generatedImageElement.getAttribute("src")).thenReturn(IMAGE_SOURCE_URL);

		String motivationalQuotePhotoUrl = motivationQuoteGatherer.getMotivationalQuotePhotoUrl();

		assertThat(motivationalQuotePhotoUrl).isNotBlank();
		assertThat(motivationalQuotePhotoUrl).isEqualTo(IMAGE_SOURCE_URL);
	}
}