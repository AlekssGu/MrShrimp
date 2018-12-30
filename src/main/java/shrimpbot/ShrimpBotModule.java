package shrimpbot;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

import server.ServerModule;
import shrimpbot.commands.BotCommand;
import shrimpbot.commands.Feedback;
import shrimpbot.commands.motivate.Motivate;
import shrimpbot.commands.Start;
import shrimpbot.commands.weather.Weather;

public class ShrimpBotModule extends AbstractModule {

	private static final String SHRIMP_BOT_NAME = "@MrShrimpBot";

	private static final String START_COMMAND = "/start";
	private static final String WEATHER_COMMAND = "/weather";
	private static final String MOTIVATE_COMMAND = "/motivate";
	private static final String FEEDBACK_COMMAND = "/feedback";

	private static final String START_COMMAND_GROUP = START_COMMAND + SHRIMP_BOT_NAME;
	private static final String WEATHER_COMMAND_GROUP = WEATHER_COMMAND + SHRIMP_BOT_NAME;
	private static final String MOTIVATE_COMMAND_GROUP = MOTIVATE_COMMAND + SHRIMP_BOT_NAME;
	private static final String FEEDBACK_COMMAND_GROUP = FEEDBACK_COMMAND + SHRIMP_BOT_NAME;


	@Override
	protected void configure() {
		installSubModules();
		bindClasses();
	}

	private void installSubModules() {
		install(new ServerModule());
	}

	private void bindClasses() {
		bind(ShrimpBot.class);
		mapBotCommandImplementations();
	}

	private void mapBotCommandImplementations() {
		MapBinder<String, BotCommand> botCommandBinder = MapBinder.newMapBinder(binder(), String.class, BotCommand.class);

		botCommandBinder.addBinding(START_COMMAND).to(Start.class);
		botCommandBinder.addBinding(WEATHER_COMMAND).to(Weather.class);
		botCommandBinder.addBinding(MOTIVATE_COMMAND).to(Motivate.class);
		botCommandBinder.addBinding(FEEDBACK_COMMAND).to(Feedback.class);

		botCommandBinder.addBinding(START_COMMAND_GROUP).to(Start.class);
		botCommandBinder.addBinding(WEATHER_COMMAND_GROUP).to(Weather.class);
		botCommandBinder.addBinding(MOTIVATE_COMMAND_GROUP).to(Motivate.class);
		botCommandBinder.addBinding(FEEDBACK_COMMAND_GROUP).to(Feedback.class);
	}
}
