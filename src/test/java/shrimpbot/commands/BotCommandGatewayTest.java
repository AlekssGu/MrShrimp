package shrimpbot.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BotCommandGatewayTest {

	public static final String COMMAND_KEY = "randomKey";
	@Mock
	private Map<String, BotCommand> botCommands;

	@Mock
	private BotCommand botCommand;

	@InjectMocks
	private BotCommandGateway botCommandGateway;

	@Test
	public void getImplementedCommand() {
		BotCommand expectedBotCommand = botCommand;
		when(botCommands.get(COMMAND_KEY)).thenReturn(expectedBotCommand);

		BotCommand actualBotCommand = botCommandGateway.getCommand(COMMAND_KEY);

		assertThat(actualBotCommand).isEqualTo(expectedBotCommand);
	}

	@Test
	public void getNotImplementedCommand() {
		BotCommand actualBotCommand = botCommandGateway.getCommand(COMMAND_KEY);

		assertThat(actualBotCommand).isInstanceOf(EmptyCommand.class);
	}
}