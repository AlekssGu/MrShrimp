package shrimpbot.commands.motivate;

import javax.inject.Inject;
import javax.inject.Provider;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import shrimpbot.ShrimpBot;
import shrimpbot.commands.BotCommand;

public class Motivate implements BotCommand {

	private final Provider<ShrimpBot> shrimpBotProvider;
	private final MotivationQuoteGatherer motivationQuoteGatherer;

	@Inject
	Motivate(Provider<ShrimpBot> shrimpBotProvider, MotivationQuoteGatherer motivationQuoteGatherer) {
		this.shrimpBotProvider = shrimpBotProvider;
		this.motivationQuoteGatherer = motivationQuoteGatherer;
	}

	@Override
	public void process(Message receivedMessage) {
		shrimpBotProvider.get().sendPhoto(prepareMotivationalPhoto(receivedMessage));
	}

	private SendPhoto prepareMotivationalPhoto(Message receivedMessage) {
		long chatId = getChatId(receivedMessage);

		return new SendPhoto()
				.setChatId(chatId)
				.setPhoto(motivationQuoteGatherer.getMotivationalQuotePhotoUrl());
	}
}