package shrimpbot.commands;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

public class BotCommandGateway {

	private final Map<String, BotCommand> botCommands;

	@Inject
	BotCommandGateway(Map<String, BotCommand> botCommands) {
		this.botCommands = botCommands;
	}

	public BotCommand getCommand(String commandKey) {
		return Optional.ofNullable(getImplementedCommand(commandKey)).orElseGet(EmptyCommand::new);
	}

	private BotCommand getImplementedCommand(String commandKey) {
		return botCommands.get(commandKey);
	}
}
