package shrimpbot.commands;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageRepository.class)
public class EmptyCommandTest {

	private static final String MESSAGE_TEXT = "messageText";

	@InjectMocks
	private EmptyCommand emptyCommand;

	@Test
	public void process() {
		Message updateMessage = Mockito.mock(Message.class);

		mockStatic(MessageRepository.class);
		Mockito.when(updateMessage.getText()).thenReturn(EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY);
		when(MessageRepository.getMessageWithParameters(EmptyCommand.BUNDLE_NAME, EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY,
				updateMessage.getText())).thenReturn(MESSAGE_TEXT);

		emptyCommand.process(updateMessage);

		PowerMockito.verifyStatic(MessageRepository.class);
		MessageRepository.getMessageWithParameters(EmptyCommand.BUNDLE_NAME, EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY, updateMessage.getText());
	}
}