package shrimpbot.commands;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import server.messages.MessageRepository;

public class BotCommandGateway {

	private final Map<String, BotCommand> botCommands;
	private final MessageRepository messageRepository;

	@Inject
	BotCommandGateway(Map<String, BotCommand> botCommands, MessageRepository messageRepository) {
		this.botCommands = botCommands;
		this.messageRepository = messageRepository;
	}

	public BotCommand getCommand(String commandKey) {
		return Optional.ofNullable(getImplementedCommand(commandKey)).orElseGet(() -> new EmptyCommand(messageRepository));
	}

	private BotCommand getImplementedCommand(String commandKey) {
		return botCommands.get(commandKey);
	}
}
