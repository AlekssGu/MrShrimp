package shrimpbot.commands;

import static server.messages.MessageRepository.getMessageWithParameters;

import org.telegram.telegrambots.meta.api.objects.Message;

public class EmptyCommand implements BotCommand {

	private static final String BUNDLE_NAME = "i18n.common.interface";

	@Override
	public void process(Message receivedMessage) {
		System.out.println(getMessageWithParameters(BUNDLE_NAME, "UNKNOWN_COMMAND", receivedMessage.getText()));
	}
}
