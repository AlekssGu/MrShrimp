package shrimpbot.commands.weather;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static shrimpbot.commands.weather.Weather.BUNDLE_NAME;
import static shrimpbot.commands.weather.Weather.WEATHER_MESSAGE_KEY;

import java.io.File;

import javax.inject.Provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;
import shrimpbot.ShrimpBot;

@RunWith(MockitoJUnitRunner.class)
public class WeatherTest {

	private static final String WEATHER_MESSAGE = "weather information message";
	private static final Long CHAT_ID = 5L;

	@Mock
	private Provider<ShrimpBot> shrimpBotProvider;

	@Mock
	private ShrimpBot shrimpBot;

	@Mock
	private WeatherInformationGatherer weatherInformationGatherer;

	@Mock
	private File screenShot;

	@Mock
	private MessageRepository messageRepository;

	@InjectMocks
	private Weather weather;

	@Test
	public void process() {
		Message receivedMessage = mock(Message.class);

		when(receivedMessage.getChatId()).thenReturn(CHAT_ID);
		when(shrimpBotProvider.get()).thenReturn(shrimpBot);
		when(weatherInformationGatherer.getMeteoprogScreenshot()).thenReturn(screenShot);
		when(messageRepository.getMessage(BUNDLE_NAME, WEATHER_MESSAGE_KEY)).thenReturn(WEATHER_MESSAGE);

		ArgumentCaptor<SendMessage> sentMessageArgument = ArgumentCaptor.forClass(SendMessage.class);
		ArgumentCaptor<SendPhoto> sentPhotoArgument = ArgumentCaptor.forClass(SendPhoto.class);

		weather.process(receivedMessage);

		verify(shrimpBot).sendMessage(sentMessageArgument.capture());
		assertThat(Long.valueOf(sentMessageArgument.getValue().getChatId())).isEqualTo(CHAT_ID);
		assertThat(sentMessageArgument.getValue().getText()).isEqualTo(WEATHER_MESSAGE);

		verify(shrimpBot).sendPhoto(sentPhotoArgument.capture());
		assertThat(Long.valueOf(sentPhotoArgument.getValue().getChatId())).isEqualTo(CHAT_ID);
		assertThat(sentPhotoArgument.getValue().getPhoto().getNewMediaFile()).isInstanceOf(File.class);
		assertThat(sentPhotoArgument.getValue().getPhoto().getNewMediaFile()).isEqualTo(screenShot);

	}
}