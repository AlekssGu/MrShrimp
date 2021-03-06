package shrimpbot.commands.weather;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Provider;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;
import shrimpbot.ShrimpBot;
import shrimpbot.commands.BotCommand;

public class Weather implements BotCommand {

	static final String BUNDLE_NAME = "i18n.common.interface";
	static final String WEATHER_MESSAGE_KEY = "WEATHER_INFORMATION_COMING_UP";

	private final Provider<ShrimpBot> shrimpBotProvider;
	private final WeatherInformationGatherer weatherInformationGatherer;
	private final MessageRepository messageRepository;

	@Inject
	Weather(Provider<ShrimpBot> shrimpBotProvider, WeatherInformationGatherer weatherInformationGatherer, MessageRepository messageRepository) {
		this.shrimpBotProvider = shrimpBotProvider;
		this.weatherInformationGatherer = weatherInformationGatherer;
		this.messageRepository = messageRepository;
	}

	@Override
	public void process(Message receivedMessage) {
		long chatId = getChatId(receivedMessage);

		shrimpBotProvider.get().sendMessage(prepareDefaultCommandMessage(chatId));
		shrimpBotProvider.get().sendPhoto(prepareScreenshotMessage(chatId));
	}

	private SendMessage prepareDefaultCommandMessage(long chatId) {
		return new SendMessage()
				.setChatId(chatId)
				.setText(messageRepository.getMessage(BUNDLE_NAME, WEATHER_MESSAGE_KEY));
	}

	private SendPhoto prepareScreenshotMessage(long chatId) {
		File screenshot = weatherInformationGatherer.getMeteoprogScreenshot();
		return new SendPhoto()
					.setChatId(chatId)
					.setPhoto(screenshot);
	}
}