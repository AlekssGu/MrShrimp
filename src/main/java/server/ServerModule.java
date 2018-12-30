package server;

import com.google.inject.AbstractModule;

import server.configuration.ConfigurationModule;
import server.messages.MessagingModule;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		installSubModules();
	}

	private void installSubModules() {
		install(new ConfigurationModule());
		install(new MessagingModule());
	}
}
