package server.configuration;

import static common.VariableEvaluator.isEmpty;

public class ShrimpBotServerConfiguration extends ServerConfiguration {

	public static final String SHRIMP_BOT_TELEGRAM_NAME = "SHRIMP_BOT_TELEGRAM_NAME";
	public static final String SHRIMP_BOT_TELEGRAM_TOKEN = "SHRIMP_BOT_TELEGRAM_TOKEN";

	protected void loadConfiguration() {
		setProperty(SHRIMP_BOT_TELEGRAM_NAME, "MrShrimp");

		loadConfigurationFromSystemProperties("systemConfigurationPropertiesFile");
	}

	private void loadConfigurationFromSystemProperties(String configurationPropertiesFile) {
		String configFile = System.getenv(configurationPropertiesFile);
		if (!isEmpty(configFile)) {
			loadProperties(configFile);
		}
	}
}
