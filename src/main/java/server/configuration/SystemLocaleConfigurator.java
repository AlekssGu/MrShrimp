package server.configuration;

import java.util.Locale;

public class SystemLocaleConfigurator {

	public static void setSystemLocale(Locale systemLocale) {
		Locale.setDefault(systemLocale);
	}

	public static Locale getSystemLocale() {
		return Locale.getDefault();
	}

}
