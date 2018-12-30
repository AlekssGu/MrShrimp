package server.messages;

import com.google.inject.AbstractModule;

public class MessagingModule extends AbstractModule {

	@Override
	protected void configure() {
		bindClasses();
	}

	private void bindClasses() {
		bind(MessageRepository.class);
	}
}
