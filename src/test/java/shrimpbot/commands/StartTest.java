package shrimpbot.commands;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import javax.inject.Provider;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
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

	@InjectMocks
	private Start start;

	@Test
	public void process() {
		mockStatic(MessageRepository.class);
		Message updateMessage = Mockito.mock(Message.class);

		when(updateMessage.getFrom()).thenReturn(user);
		when(user.getUserName()).thenReturn(USERNAME);
		when(shrimpBotProvider.get()).thenReturn(shrimpBot);
		PowerMockito.when(MessageRepository.getMessageWithParameters(Start.BUNDLE_NAME, Start.WELCOME_MESSAGE_KEY,
				user.getUserName())).thenReturn(RANDOM_MESSAGE_WITH_USERNAME);

		ArgumentCaptor<SendMessage> sentMessageArgument = ArgumentCaptor.forClass(SendMessage.class);

		start.process(updateMessage);
		verify(shrimpBot).sendMessage(sentMessageArgument.capture());

		Assertions.assertThat(sentMessageArgument.getValue().getText()).contains("alekssgu");
	}
}