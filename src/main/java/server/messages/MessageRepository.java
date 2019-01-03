package server.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

public class MessageRepository {

	private final ResourceBundleSupplier resourceBundleSupplier;

	@Inject
	MessageRepository(ResourceBundleSupplier resourceBundleSupplier) {
		this.resourceBundleSupplier = resourceBundleSupplier;
	}

	public ResourceBundle getMessagesBundle(String bundleName) {
		return resourceBundleSupplier.getBundle(bundleName, Locale.getDefault());
	}

	public String getMessage(String bundleName, String messageKey) {
		return getMessagesBundle(bundleName).getString(messageKey);
	}

	public String getMessageWithParameters(String bundleName, String messageKey, Object... params) {
		return MessageFormat.format(getMessage(bundleName, messageKey), params);
	}
}
