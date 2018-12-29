package server.configuration;

public class ShrimpBotConfiguration extends ServerConfiguration {

	public static final String SHRIMP_BOT_TELEGRAM_NAME = "SHRIMP_BOT_TELEGRAM_NAME";
	public static final String SHRIMP_BOT_TELEGRAM_TOKEN = "SHRIMP_BOT_TELEGRAM_TOKEN";

	public ShrimpBotConfiguration() {
		super.loadConfiguration();
	}

	@Override
	protected void loadConfiguration() {
		setProperty(SHRIMP_BOT_TELEGRAM_NAME, "MrShrimp");
	}
}
