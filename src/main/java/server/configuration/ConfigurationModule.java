package server.configuration;

import com.google.inject.AbstractModule;

public class ConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		bindClasses();
	}

	private void bindClasses() {
		bind(AbstractServerConfiguration.class).to(ServerConfiguration.class);
		bind(ServerConfiguration.class).to(ShrimpBotConfiguration.class);
		bind(SystemLocaleConfigurator.class);
	}
}
