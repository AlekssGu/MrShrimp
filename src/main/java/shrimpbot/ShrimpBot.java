package shrimpbot;

import javax.inject.Inject;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import server.configuration.ShrimpBotConfiguration;
import shrimpbot.commands.BotCommand;
import shrimpbot.commands.BotCommandGateway;

public class ShrimpBot extends TelegramLongPollingBot {

	private final ShrimpBotConfiguration serverConfiguration;
	private final BotCommandGateway botCommandGateway;

	@Inject
	ShrimpBot(ShrimpBotConfiguration shrimpBotConfiguration, BotCommandGateway botCommandGateway) {
		this.serverConfiguration = shrimpBotConfiguration;
		this.botCommandGateway = botCommandGateway;
	}

	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			Message receivedMessage = update.getMessage();
			BotCommand command = botCommandGateway.getCommand(receivedMessage.getText());

			command.process(receivedMessage);
		}
	}

	public String getBotUsername() {
		return serverConfiguration.getProperty(ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_NAME);
	}

	public String getBotToken() {
		return serverConfiguration.getProperty(ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_TOKEN);
	}

	public void sendMessage(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void sendPhoto(SendPhoto message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}