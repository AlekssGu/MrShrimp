package shrimpbot.commands;

import static server.messages.MessageRepository.getMessageWithParameters;

import javax.inject.Inject;
import javax.inject.Provider;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import shrimpbot.ShrimpBot;

public class Start implements BotCommand {

	private static final String BUNDLE_NAME = "i18n.common.start";

	private final Provider<ShrimpBot> shrimpBotProvider;

	@Inject
	Start(Provider<ShrimpBot> shrimpBotProvider) {
		this.shrimpBotProvider = shrimpBotProvider;
	}

	@Override
	public void process(Message receivedMessage) {
		shrimpBotProvider.get().sendMessage(prepareWelcomeMessage(receivedMessage));
	}

	private SendMessage prepareWelcomeMessage(Message receivedMessage) {
		long chatId = getChatId(receivedMessage);

		return new SendMessage()
				.setChatId(chatId)
				.setText(getWelcomeText(receivedMessage));
	}

	private String getWelcomeText(Message receivedMessage) {
		return getMessageWithParameters(BUNDLE_NAME, "HELLO_TXT", receivedMessage.getFrom().getUserName());
	}
}
