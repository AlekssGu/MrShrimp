package shrimpbot.commands.weather;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static server.messages.MessageRepository.getMessage;
import static shrimpbot.commands.weather.Weather.BUNDLE_NAME;
import static shrimpbot.commands.weather.Weather.WEATHER_MESSAGE_KEY;

import java.io.File;

import javax.inject.Provider;

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
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;
import shrimpbot.ShrimpBot;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageRepository.class)
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

	@InjectMocks
	private Weather weather;

	@Test
	public void process() {
		mockStatic(MessageRepository.class);
		Message receivedMessage = Mockito.mock(Message.class);

		when(receivedMessage.getChatId()).thenReturn(CHAT_ID);
		when(shrimpBotProvider.get()).thenReturn(shrimpBot);
		when(weatherInformationGatherer.getMeteoprogScreenshot()).thenReturn(screenShot);
		PowerMockito.when(getMessage(BUNDLE_NAME, WEATHER_MESSAGE_KEY)).thenReturn(WEATHER_MESSAGE);

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