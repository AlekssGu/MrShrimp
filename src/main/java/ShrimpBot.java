import javax.inject.Inject;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import server.configuration.ShrimpBotServerConfiguration;

public class ShrimpBot extends TelegramLongPollingBot {

	private final ShrimpBotServerConfiguration serverConfiguration;

	@Inject
	ShrimpBot(ShrimpBotServerConfiguration shrimpBotServerConfiguration) {
		this.serverConfiguration = shrimpBotServerConfiguration;
	}

	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();

			SendMessage message = new SendMessage()
					.setChatId(chat_id)
					.setText(message_text);
			try {
				execute(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	public String getBotUsername() {
		return serverConfiguration.getProperty(ShrimpBotServerConfiguration.SHRIMP_BOT_TELEGRAM_NAME);
	}

	public String getBotToken() {
		return serverConfiguration.getProperty(ShrimpBotServerConfiguration.SHRIMP_BOT_TELEGRAM_TOKEN);
	}
}