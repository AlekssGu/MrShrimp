package server.messages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
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

	@Mock
	private ResourceBundleSupplier resourceBundleSupplier;

	@InjectMocks
	private MessageRepository messageRepository;

	@Test
	public void getMessagesBundle() {
		mockStatic(ResourceBundle.class);
		PowerMockito.when(ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault())).thenReturn(resourceBundle);
		when(resourceBundleSupplier.getBundle(any(), any(Locale.class))).thenReturn(resourceBundle);

		ResourceBundle actualResourceBundle = messageRepository.getMessagesBundle(BUNDLE_NAME);
		assertThat(actualResourceBundle).isEqualTo(resourceBundle);
	}

	@Test
	public void getMessage() {
		mockStatic(ResourceBundle.class);
		PowerMockito.when(ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault())).thenReturn(resourceBundle);
		when(resourceBundleSupplier.getBundle(any(), any(Locale.class))).thenReturn(resourceBundle);
		when(messageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY)).thenReturn(MESSAGE_TEXT);

		String translatedMessage = messageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY);
		assertThat(translatedMessage).isEqualTo(MESSAGE_TEXT);
	}

	@Test
	public void getMessageWithParameters() {
		mockStatic(ResourceBundle.class);
		PowerMockito.when(ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault())).thenReturn(resourceBundle);
		when(resourceBundleSupplier.getBundle(any(), any(Locale.class))).thenReturn(resourceBundle);
		when(messageRepository.getMessage(BUNDLE_NAME, MESSAGE_KEY)).thenReturn(MESSAGE_TEXT_WITH_PARAMETER);
		when(messageRepository.getMessageWithParameters(BUNDLE_NAME, MESSAGE_KEY, MESSAGE_PARAMETER_VALUE)).thenReturn(MESSAGE_TEXT_WITH_PARAMETER_VALUE);

		String translatedMessage = messageRepository.getMessageWithParameters(BUNDLE_NAME, MESSAGE_KEY, MESSAGE_PARAMETER_VALUE);

		assertThat(translatedMessage).isEqualTo(MESSAGE_TEXT_WITH_PARAMETER_VALUE);
	}
}