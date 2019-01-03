package shrimpbot.commands;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmptyCommandTest {

	private static final String MESSAGE_TEXT = "messageText";

	@Mock
	private MessageRepository messageRepository;

	@InjectMocks
	private EmptyCommand emptyCommand;

	@Test
	public void process() {
		Message updateMessage = Mockito.mock(Message.class);

		Mockito.when(updateMessage.getText()).thenReturn(EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY);
		when(messageRepository.getMessageWithParameters(EmptyCommand.BUNDLE_NAME, EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY,
				updateMessage.getText())).thenReturn(MESSAGE_TEXT);

		emptyCommand.process(updateMessage);

		verify(messageRepository).getMessageWithParameters(EmptyCommand.BUNDLE_NAME, EmptyCommand.UNKNOWN_COMMAND_MESSAGE_KEY, updateMessage.getText());
	}
}