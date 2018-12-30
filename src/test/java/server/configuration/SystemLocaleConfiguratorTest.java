package server.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SystemLocaleConfiguratorTest {

	@Test
	public void configureLocale() {
		SystemLocaleConfigurator.setSystemLocale(Locale.US);
		assertThat(SystemLocaleConfigurator.getSystemLocale()).isEqualTo(Locale.US);

		SystemLocaleConfigurator.setSystemLocale(Locale.CANADA_FRENCH);
		assertThat(SystemLocaleConfigurator.getSystemLocale()).isEqualTo(Locale.CANADA_FRENCH);

		Locale localeLV = new Locale("lv_LV");
		SystemLocaleConfigurator.setSystemLocale(localeLV);
		assertThat(SystemLocaleConfigurator.getSystemLocale()).isEqualTo(localeLV);
	}
}