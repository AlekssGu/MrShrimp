import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import shrimpbot.ShrimpBot;
import shrimpbot.ShrimpBotModule;

public class Main {

	public static void main(String[] args) {
		initializeAndRun();
	}

	private static void initializeAndRun() {
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		registerBots(botsApi);
	}

	private static void registerBots(TelegramBotsApi botsApi) {
		try {
			botsApi.registerBot(getShrimpBot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}

	private static ShrimpBot getShrimpBot() {
		Injector injector = Guice.createInjector(new ShrimpBotModule());
		return injector.getInstance(ShrimpBot.class);
	}
}
