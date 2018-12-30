package shrimpbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotCommand {

	void process(Message receivedMessage);

	default long getChatId(Message receivedMessage) {
		return receivedMessage.getChatId();
	}
}
