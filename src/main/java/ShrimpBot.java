import static server.messages.MessageRepository.getMessage;
import static server.messages.MessageRepository.getMessageWithParameters;

import javax.inject.Inject;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import server.configuration.ShrimpBotConfiguration;

public class ShrimpBot extends TelegramLongPollingBot {

	private static final String BUNDLE_NAME = "i18n.common.interface";

	private final ShrimpBotConfiguration serverConfiguration;

	@Inject
	ShrimpBot(ShrimpBotConfiguration shrimpBotConfiguration) {
		this.serverConfiguration = shrimpBotConfiguration;
	}

	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			Message receivedMessage = update.getMessage();
			long chatId = update.getMessage().getChatId();

			if (receivedMessage.getText().equals("/start")) {
				SendMessage message = new SendMessage().setChatId(chatId).setText(
						getMessageWithParameters(BUNDLE_NAME, "HELLO_TXT", receivedMessage.getFrom().getUserName()));
				sendMessage(message);
			} else if(receivedMessage.getText().equals("/motivate")) {
				SendMessage message = new SendMessage()
						.setChatId(chatId)
						.setText(getMessageWithParameters(BUNDLE_NAME, "MOTIVATIONAL_QUOTE", "You can do it!"));
				sendMessage(message);
			} else if(receivedMessage.getText().equals("/weather")) {
				SendMessage message = new SendMessage()
						.setChatId(chatId)
						.setText(getMessage(BUNDLE_NAME, "WEATHER_INFORMATION_COMING_UP"));
				sendMessage(message);
			} else {
				SendMessage message = new SendMessage()
						.setChatId(chatId)
						.setText(getMessage(BUNDLE_NAME, "UNKNOWN_COMMAND"));
				sendMessage(message);
			}
		}
	}

	public String getBotUsername() {
		return serverConfiguration.getProperty(ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_NAME);
	}

	public String getBotToken() {
		return serverConfiguration.getProperty(ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_TOKEN);
	}

	private void sendMessage(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}