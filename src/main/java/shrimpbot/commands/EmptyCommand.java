package shrimpbot.commands;

import static server.messages.MessageRepository.getMessageWithParameters;

import org.telegram.telegrambots.meta.api.objects.Message;

public class EmptyCommand implements BotCommand {

	static final String BUNDLE_NAME = "i18n.common.interface";
	static final String UNKNOWN_COMMAND_MESSAGE_KEY = "UNKNOWN_COMMAND";

	@Override
	public void process(Message receivedMessage) {
		System.out.println(getMessageWithParameters(BUNDLE_NAME, UNKNOWN_COMMAND_MESSAGE_KEY, receivedMessage.getText()));
	}
}
