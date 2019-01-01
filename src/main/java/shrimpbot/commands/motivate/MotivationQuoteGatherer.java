package shrimpbot.commands.motivate;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MotivationQuoteGatherer {

	static final String GENERATED_IMAGE_CLASS_NAME = "generated-image";
	static final String BTN_GENERATE_CLASS_NAME = "btn-generate";

	private static final String INSPIROBOT_WEBSITE_URL = "https://inspirobot.me";
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 728;

	String getMotivationalQuotePhotoUrl() {
		return generateNewMotivationalImageUrl();
	}

	private String generateNewMotivationalImageUrl() {
		return getMotivationalImageUrl();
	}

	private String getMotivationalImageUrl() {
		final WebDriver driver = new FirefoxDriver();
		final String link = INSPIROBOT_WEBSITE_URL;

		driver.get(link);
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		driver.findElement(By.className(BTN_GENERATE_CLASS_NAME)).click();

		return findImageUrl(driver);
	}

	private String findImageUrl(WebDriver driver) {
		String imageSrc;

		while(true) {
			imageSrc = driver.findElement(By.className(GENERATED_IMAGE_CLASS_NAME)).getAttribute("src");
			if(imageSrc.contains("https://")) {
				break;
			}
		}

		driver.quit();

		return imageSrc;
	}
}
