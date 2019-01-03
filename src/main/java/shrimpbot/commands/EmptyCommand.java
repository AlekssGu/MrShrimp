package shrimpbot.commands;

import javax.inject.Inject;

import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;

public class EmptyCommand implements BotCommand {

	static final String BUNDLE_NAME = "i18n.common.interface";
	static final String UNKNOWN_COMMAND_MESSAGE_KEY = "UNKNOWN_COMMAND";

	private final MessageRepository messageRepository;

	@Inject
	EmptyCommand(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public void process(Message receivedMessage) {
		System.out.println(messageRepository.getMessageWithParameters(BUNDLE_NAME, UNKNOWN_COMMAND_MESSAGE_KEY, receivedMessage.getText()));
	}
}
