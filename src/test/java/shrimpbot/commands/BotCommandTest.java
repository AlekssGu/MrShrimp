package shrimpbot.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.telegram.telegrambots.meta.api.objects.Message;

@RunWith(Parameterized.class)
public class BotCommandTest {


	private BotCommand botCommand;

	public BotCommandTest(BotCommand botCommand) {
		this.botCommand = botCommand;
	}

	@Test
	public void process() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		String messageText = prepareRandomString();
		Message updateMessage = mock(Message.class);
		when(updateMessage.getText()).thenReturn(messageText);

		botCommand.process(updateMessage);

		assertThat(outContent.toString()).isEqualToIgnoringNewLines(messageText);
	}

	@Test
	public void getChatId() {
		Message updateMessage = mock(Message.class);
		long messageChatId = getRandomNumber();
		when(updateMessage.getChatId()).thenReturn(messageChatId);

		long chatId = botCommand.getChatId(updateMessage);

		assertThat(chatId).isEqualTo(messageChatId);
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		return Arrays.asList(
				new Object[]{new DummyImplementation()},
				new Object[]{new AnotherDummyImplementation()}
		);
	}

	private String prepareRandomString() {
		String randomString = "test" + getRandomNumber();
		return Base64.encodeBase64(randomString.getBytes()).toString();
	}

	private long getRandomNumber() {
		return (int) Math.random() * 49 + 1;
	}

	private static class DummyImplementation implements BotCommand {
		@Override
		public void process(Message receivedMessage) {
			System.out.println(receivedMessage.getText());
		}

		@Override
		public long getChatId(Message receivedMessage) {
			return receivedMessage.getChatId();
		}
	}

	private static class AnotherDummyImplementation implements BotCommand {
		@Override
		public void process(Message receivedMessage) {
			System.out.println(receivedMessage.getText());
		}

		@Override
		public long getChatId(Message receivedMessage) {
			return receivedMessage.getChatId();
		}
	}
}