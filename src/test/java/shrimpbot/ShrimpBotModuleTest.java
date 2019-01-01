package shrimpbot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.inject.Guice;
import com.google.inject.Injector;

import server.ServerModule;
import shrimpbot.commands.Start;

@RunWith(MockitoJUnitRunner.class)
public class ShrimpBotModuleTest {

	@Test
	public void configure() {
		Injector injector = Guice.createInjector(new ShrimpBotModule());

		assertThat(injector.getProvider(ServerModule.class)).isNotNull();
		assertThat(injector.getProvider(ShrimpBot.class)).isNotNull();
		assertThat(injector.getProvider(Start.class)).isNotNull();
	}
}