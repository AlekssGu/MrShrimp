import javax.inject.Inject;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import server.configuration.ShrimpBotServerConfiguration;

public class Main {

	private final ShrimpBot shrimpBot;

	@Inject
	Main(ShrimpBot shrimpBot) {
		this.shrimpBot = shrimpBot;
	}

	public static void main(String[] args) {

		ShrimpBotServerConfiguration shrimpBotServerConfiguration = new ShrimpBotServerConfiguration();

		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new ShrimpBot(shrimpBotServerConfiguration));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
}
