package server.messages;

import java.util.Locale;
import java.util.ResourceBundle;

class ResourceBundleSupplier {

	ResourceBundle getBundle(String bundleName, Locale locale) {
		return ResourceBundle.getBundle(bundleName, locale);
	}
}
