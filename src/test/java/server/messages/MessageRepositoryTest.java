package server.messages;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageRepositoryTest {

	private static final String EXISTING_BUNDLE_NAME = "i18n.common.interface";
	private static final String EXISTING_MESSAGE_KEY = "UNKNOWN_COMMAND";
	private static final String EXISTING_MESSAGE_TEXT = "Unknown command requested - {0}";

	@Mock
	private ResourceBundle resourceBundle;

	@Test
	public void getMessagesBundle() {
		ResourceBundle actualResourceBundle = MessageRepository.getMessagesBundle(EXISTING_BUNDLE_NAME);
		assertThat(actualResourceBundle).isEqualTo(ResourceBundle.getBundle(EXISTING_BUNDLE_NAME));
	}

	@Test
	public void getMessage() {
		String translatedMessage = MessageRepository.getMessage(EXISTING_BUNDLE_NAME, EXISTING_MESSAGE_KEY);
		String expectedString = ResourceBundle.getBundle(EXISTING_BUNDLE_NAME).getString(EXISTING_MESSAGE_KEY);

		assertThat(translatedMessage).isEqualTo(expectedString);
	}

	@Test
	public void getMessageWithParameters() {
		String messageParameter = "test";
		String translatedMessage = MessageRepository.getMessageWithParameters(EXISTING_BUNDLE_NAME, EXISTING_MESSAGE_KEY, messageParameter);
		String expectedString = MessageFormat.format(ResourceBundle.getBundle(EXISTING_BUNDLE_NAME).getString(EXISTING_MESSAGE_KEY),
				messageParameter);

		assertThat(translatedMessage).isEqualTo(expectedString);
	}
}