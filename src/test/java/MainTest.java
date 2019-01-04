import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import shrimpbot.ShrimpBot;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Main.class, Guice.class })
public class MainTest {

	@Mock
	private ShrimpBot shrimpBot;

	@Mock
	private Injector injector;

	@Mock
	private TelegramBotsApi telegramBotsApi;

	@Mock
	private BotSession botSession;

	@Test
	public void main() throws Exception {
		PowerMockito.mockStatic(Guice.class);
		whenNew(TelegramBotsApi.class).withAnyArguments().thenReturn(telegramBotsApi);
		when(telegramBotsApi.registerBot(any(ShrimpBot.class))).thenReturn(botSession);
		when(Guice.createInjector(any(AbstractModule.class))).thenReturn(injector);
		when(injector.getInstance(ShrimpBot.class)).thenReturn(shrimpBot);

		String[] args = {};
		Main.main(args);
	}

	@Test
	public void mainThrowsException() throws Exception {
		PowerMockito.mockStatic(Guice.class);
		whenNew(TelegramBotsApi.class).withAnyArguments().thenReturn(telegramBotsApi);
		doThrow(new TelegramApiRequestException("Request exception")).when(telegramBotsApi).registerBot(shrimpBot);
		when(Guice.createInjector(any(AbstractModule.class))).thenReturn(injector);
		when(injector.getInstance(ShrimpBot.class)).thenReturn(shrimpBot);

		String[] args = {};
		Main.main(args);
	}
}