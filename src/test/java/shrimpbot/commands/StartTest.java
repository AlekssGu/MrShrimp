package shrimpbot.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static shrimpbot.commands.Start.BUNDLE_NAME;
import static shrimpbot.commands.Start.WELCOME_MESSAGE_KEY;

import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import server.messages.MessageRepository;
import shrimpbot.ShrimpBot;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageRepository.class)
public class StartTest {


	private static final String USERNAME = "alekssgu";
	private static final String RANDOM_MESSAGE_WITH_USERNAME = "randomMessage with username " + USERNAME;

	@Mock
	private Provider<ShrimpBot> shrimpBotProvider;

	@Mock
	private ShrimpBot shrimpBot;

	@Mock
	private User user;

	@Mock
	private MessageRepository messageRepository;

	@InjectMocks
	private Start start;

	@Test
	public void process() {
		Message receivedMessage = mock(Message.class);

		when(receivedMessage.getFrom()).thenReturn(user);
		when(user.getUserName()).thenReturn(USERNAME);
		when(shrimpBotProvider.get()).thenReturn(shrimpBot);
		when(messageRepository.getMessageWithParameters(BUNDLE_NAME, WELCOME_MESSAGE_KEY, user.getUserName())).thenReturn(RANDOM_MESSAGE_WITH_USERNAME);

		ArgumentCaptor<SendMessage> sentMessageArgument = ArgumentCaptor.forClass(SendMessage.class);

		start.process(receivedMessage);

		verify(shrimpBot).sendMessage(sentMessageArgument.capture());
		assertThat(sentMessageArgument.getValue().getText()).contains("alekssgu");
	}
}