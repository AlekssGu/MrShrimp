package server.configuration;

import static common.VariableEvaluator.isEmpty;

import java.util.Locale;

public class ServerConfiguration extends AbstractServerConfiguration {

	public static final String DEFAULT_SYSTEM_LOCALE = "DEFAULT_SYSTEM_LOCALE";

	private final Locale defaultSystemLocale = Locale.US;

	@Override
	protected void loadConfiguration() {
		configureAndSetDefaultSystemLocale();
		loadConfigurationFromSystemProperties("systemConfigurationPropertiesFile");
	}

	private void configureAndSetDefaultSystemLocale() {
		setProperty(DEFAULT_SYSTEM_LOCALE, defaultSystemLocale.toString());
		SystemLocaleConfigurator.setSystemLocale(defaultSystemLocale);
	}

	private void loadConfigurationFromSystemProperties(String configurationPropertiesFile) {
		String configFile = System.getenv(configurationPropertiesFile);
		if (!isEmpty(configFile)) {
			loadProperties(configFile);
		}
	}
}
