package shrimpbot.commands;

import javax.inject.Inject;
import javax.inject.Provider;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import server.messages.MessageRepository;
import shrimpbot.ShrimpBot;

public class Start implements BotCommand {

	static final String BUNDLE_NAME = "i18n.common.start";
	static final String WELCOME_MESSAGE_KEY = "HELLO_TXT";

	private final Provider<ShrimpBot> shrimpBotProvider;
	private final MessageRepository messageRepository;

	@Inject
	Start(Provider<ShrimpBot> shrimpBotProvider, MessageRepository messageRepository) {
		this.shrimpBotProvider = shrimpBotProvider;
		this.messageRepository = messageRepository;
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
		return messageRepository.getMessageWithParameters(BUNDLE_NAME, WELCOME_MESSAGE_KEY, receivedMessage.getFrom().getUserName());
	}
}
