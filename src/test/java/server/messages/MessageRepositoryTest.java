package server.messages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MessageRepository.class)
public class MessageRepositoryTest {

	private static final String BUNDLE_NAME = "randomBundle";
	private static final String MESSAGE_KEY = "randomKey";
	private static final String MESSAGE_TEXT = "randomString";
	private static final String MESSAGE_TEXT_WITH_PARAMETER = "randomString - {0}";
	private static final String MESSAGE_PARAMETER_VALUE = "test";
	private static final String MESSAGE_TEXT_WITH_PARAMETER_VALUE = "randomString - " + MESSAGE_PARAMETER_VALUE;

	@Mock
	private ResourceBundle resourceBundle;

	@Before
	public void beforeEachTestMethod() {
		mockStatic(MessageRepository.class);
		when(MessageRepository.getMessagesBundle(BUNDLE_NAME)).thenReturn(resourceBundle);
	}

	@Test
	public void getMessagesBundle() {
		ResourceBundle actualResourceBundle = MessageRepository.getMessagesBundle(BUNDLE_NAME);
		assertThat(actualResourceBundle).isEqualTo(resourceBundle);
	}

	@Test
	public void getMessage() {
		when(MessageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY)).thenReturn(MESSAGE_TEXT);

		String translatedMessage = MessageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY);
		assertThat(translatedMessage).isEqualTo(MESSAGE_TEXT);
	}

	@Test
	public void getMessageWithParameters() {
		when(MessageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY)).thenReturn(MESSAGE_TEXT_WITH_PARAMETER);
		when(MessageRepository.getMessageWithParameters(BUNDLE_NAME, MESSAGE_KEY, MESSAGE_PARAMETER_VALUE)).thenReturn(MESSAGE_TEXT_WITH_PARAMETER_VALUE);

		String translatedMessage = MessageRepository.getMessageWithParameters(BUNDLE_NAME, MESSAGE_KEY, MESSAGE_PARAMETER_VALUE);

		assertThat(translatedMessage).isEqualTo(MESSAGE_TEXT_WITH_PARAMETER_VALUE);
	}

}