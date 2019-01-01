package shrimpbot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static server.configuration.ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_NAME;
import static server.configuration.ShrimpBotConfiguration.SHRIMP_BOT_TELEGRAM_TOKEN;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import server.configuration.ShrimpBotConfiguration;
import shrimpbot.commands.BotCommand;
import shrimpbot.commands.BotCommandGateway;

@RunWith(MockitoJUnitRunner.class)
public class ShrimpBotTest {

	private static final long CHAT_ID = 1L;
	private static final String MESSAGE_TEXT = "randomMessageText";

	@Mock
	private ShrimpBotConfiguration shrimpBotConfiguration;

	@Mock
	private BotCommandGateway botCommandGateway;

	@Mock
	private Update receivedUpdate;

	@Spy
	@InjectMocks
	private ShrimpBot shrimpBot;

	@Test
	public void onUpdateReceived() {
		BotCommand randomCommand = mock(BotCommand.class);
		Message randomMessage = mock(Message.class);

		when(receivedUpdate.hasMessage()).thenReturn(true);
		when(receivedUpdate.getMessage()).thenReturn(randomMessage);
		when(randomMessage.hasText()).thenReturn(true);
		when(randomMessage.getText()).thenReturn("randomText");
		when(botCommandGateway.getCommand("randomText")).thenReturn(randomCommand);

		doNothing().when(randomCommand).process(randomMessage);
		doCallRealMethod().when(shrimpBot).onUpdateReceived(receivedUpdate);

		shrimpBot.onUpdateReceived(receivedUpdate);

		verify(randomCommand).process(receivedUpdate.getMessage());
	}

	@Test
	public void onUpdateReceivedWithoutMessage() {
		BotCommand randomCommand = mock(BotCommand.class);
		when(receivedUpdate.hasMessage()).thenReturn(false);
		doCallRealMethod().when(shrimpBot).onUpdateReceived(receivedUpdate);

		shrimpBot.onUpdateReceived(receivedUpdate);

		verify(randomCommand, never()).process(receivedUpdate.getMessage());
	}

	@Test
	public void onUpdateReceivedWithoutMessageText() {
		BotCommand randomCommand = mock(BotCommand.class);
		Message randomMessage = mock(Message.class);

		when(receivedUpdate.hasMessage()).thenReturn(true);
		when(receivedUpdate.getMessage()).thenReturn(randomMessage);
		when(randomMessage.hasText()).thenReturn(false);
		doCallRealMethod().when(shrimpBot).onUpdateReceived(receivedUpdate);

		shrimpBot.onUpdateReceived(receivedUpdate);

		verify(randomCommand, never()).process(receivedUpdate.getMessage());
	}

	@Test
	public void getBotUsername() {
		when(shrimpBotConfiguration.getProperty(SHRIMP_BOT_TELEGRAM_NAME)).thenReturn(SHRIMP_BOT_TELEGRAM_NAME);

		String actualBotUsername = shrimpBot.getBotUsername();

		assertThat(actualBotUsername).isEqualTo(SHRIMP_BOT_TELEGRAM_NAME);
	}

	@Test
	public void getBotToken() {
		when(shrimpBotConfiguration.getProperty(SHRIMP_BOT_TELEGRAM_TOKEN)).thenReturn(SHRIMP_BOT_TELEGRAM_TOKEN);

		String actualBotToken = shrimpBot.getBotToken();

		assertThat(actualBotToken).isEqualTo(SHRIMP_BOT_TELEGRAM_TOKEN);
	}

	@Test
	public void sendMessage() throws TelegramApiException {
		SendMessage messageForSending = createTextMessageForSending();
		Message actualMessageSent = Mockito.mock(Message.class);

		Mockito.doReturn(actualMessageSent).when(shrimpBot).execute(messageForSending);

		when(actualMessageSent.getText()).thenReturn(MESSAGE_TEXT);
		when(actualMessageSent.getChatId()).thenReturn(CHAT_ID);
		when(shrimpBot.execute(messageForSending)).thenReturn(actualMessageSent);

		shrimpBot.sendMessage(messageForSending);

		assertThat(actualMessageSent.getChatId()).isEqualTo(Long.valueOf(messageForSending.getChatId()));
		assertThat(actualMessageSent.getText()).isEqualTo(messageForSending.getText());
	}

	@Test(expected = TelegramApiException.class)
	public void sendMessageThrowsTelegramError() throws TelegramApiException {
		SendMessage messageForSending = Mockito.mock(SendMessage.class);
		when(shrimpBot.execute(messageForSending)).thenThrow(new TelegramApiException());

		shrimpBot.sendMessage(messageForSending);
	}

	@Test
	public void sendPhoto() throws IOException, TelegramApiException {
		SendPhoto messageForSending = createPhotoMessageForSending();
		Message actualMessageSent = Mockito.mock(Message.class);

		doNothing().when(shrimpBot).sendPhoto(messageForSending);
		when(actualMessageSent.getChatId()).thenReturn(CHAT_ID);

		shrimpBot.sendPhoto(messageForSending);

		assertThat(actualMessageSent.getChatId()).isEqualTo(Long.valueOf(messageForSending.getChatId()));
	}

	@Test(expected = TelegramApiException.class)
	public void sendPhotoThrowsTelegramError() throws TelegramApiException, IOException {
		SendPhoto messageForSending = createPhotoMessageForSending();
		when(shrimpBot.execute(messageForSending)).thenThrow(new TelegramApiException());

		shrimpBot.sendPhoto(messageForSending);
	}

	private SendMessage createTextMessageForSending() {
		return new SendMessage()
				.setChatId(CHAT_ID)
				.setText(MESSAGE_TEXT);
	}

	private SendPhoto createPhotoMessageForSending() throws IOException {
		File imageFile = new File("testImage.jpg");
		imageFile.createNewFile();

		return new SendPhoto()
				.setChatId(CHAT_ID)
				.setPhoto(imageFile);
	}
}