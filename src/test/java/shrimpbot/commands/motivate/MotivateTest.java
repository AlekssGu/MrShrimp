package shrimpbot.commands.motivate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import shrimpbot.ShrimpBot;

@RunWith(MockitoJUnitRunner.class)
public class MotivateTest {

	private static final Long CHAT_ID = 5L;
	private static final String IMAGE_URL = "https://randomimageurl.com";
	@Mock
	private Provider<ShrimpBot> shrimpBotProvider;

	@Mock
	private ShrimpBot shrimpBot;

	@Mock
	private MotivationQuoteGatherer motivationQuoteGatherer;

	@Mock
	private Message receivedMessage;

	@InjectMocks
	private Motivate motivate;

	@Test
	public void process() {
		when(receivedMessage.getChatId()).thenReturn(CHAT_ID);
		when(shrimpBotProvider.get()).thenReturn(shrimpBot);
		when(motivationQuoteGatherer.getMotivationalQuotePhotoUrl()).thenReturn(IMAGE_URL);

		ArgumentCaptor<SendPhoto> sentPhotoArgument = ArgumentCaptor.forClass(SendPhoto.class);

		motivate.process(receivedMessage);

		verify(motivationQuoteGatherer).getMotivationalQuotePhotoUrl();
		verify(shrimpBot).sendPhoto(sentPhotoArgument.capture());

		assertThat(Long.valueOf(sentPhotoArgument.getValue().getChatId())).isEqualTo(CHAT_ID);
		assertThat(sentPhotoArgument.getValue().getPhoto().getAttachName()).isEqualTo(IMAGE_URL);
	}
}