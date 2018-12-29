package server.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageRepository {

	public static ResourceBundle getMessagesBundle(String bundleName) {
		return ResourceBundle.getBundle(bundleName, Locale.getDefault());
	}

	public static String getMessage(String bundleName, String messageKey) {
		return getMessagesBundle(bundleName).getString(messageKey);
	}

	public static String getMessageWithParameters(String bundleName, String messageKey, Object... params) {
		return MessageFormat.format(getMessage(bundleName, messageKey), params);
	}
}
