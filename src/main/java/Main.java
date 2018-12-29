import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import server.configuration.ShrimpBotConfiguration;

public class Main {

	public static void main(String[] args) {

		ShrimpBotConfiguration shrimpBotConfiguration = new ShrimpBotConfiguration();

		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new ShrimpBot(shrimpBotConfiguration));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
}
