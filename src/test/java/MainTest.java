import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

import com.google.inject.Guice;
import com.google.inject.Injector;

import shrimpbot.ShrimpBot;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Guice.class, TelegramBotsApi.class})
public class MainTest {

	@Mock
	private ShrimpBot shrimpBot;

	@Mock
	private Injector injector;

	@Mock
	private BotSession botSession;

	@Test
	public void main() throws TelegramApiRequestException {
//		TelegramBotsApi telegramBotsApi = PowerMockito.mock(TelegramBotsApi.class);
//		PowerMockito.mockStatic(Guice.class);
//		when(Guice.createInjector(any(AbstractModule.class))).thenReturn(injector);
//		when(injector.getInstance(ShrimpBot.class)).thenReturn(shrimpBot);
//		when(telegramBotsApi.registerBot(any(LongPollingBot.class))).thenReturn(botSession);

		String[] args = {};
		Main.main(args);
	}
}