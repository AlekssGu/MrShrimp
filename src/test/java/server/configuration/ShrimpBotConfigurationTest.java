package server.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShrimpBotConfigurationTest {

	@InjectMocks
	private ShrimpBotConfiguration shrimpBotConfiguration;

	@Test
	public void loadConfiguration() {
		shrimpBotConfiguration.loadConfiguration();
	}
}