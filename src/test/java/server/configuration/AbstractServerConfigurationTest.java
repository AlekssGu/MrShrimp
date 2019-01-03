package server.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AbstractServerConfiguration.class, File.class, FileInputStream.class})
public class AbstractServerConfigurationTest {

	private static final String RANDOM_PROPERTY_VALUE = "randomValue";
	private static final String RANDOM_PROPERTY_KEY = "randomKey";
	private static final String SAMPLE_PROPERTY_VALUE = "sampleValue";
	private static final String SAMPLE_PROPERTY_KEY = "sampleKey";
	private static final String PROPERTY_FILE_PATH = "C:/randomFile.txt";

	@InjectMocks
	private AbstractServerConfiguration abstractServerConfiguration = new AbstractServerConfiguration() {
		@Override
		protected void loadConfiguration() {
			this.properties = new Properties();
		}
	};

	@Test
	public void getProperty() {
		loadSampleConfiguration();
		String actualKeyValue = abstractServerConfiguration.getProperty(RANDOM_PROPERTY_KEY);
		assertThat(actualKeyValue).isEqualTo(RANDOM_PROPERTY_VALUE);
	}

	@Test
	public void getNonExistingProperty() {
		String actualKeyValue = abstractServerConfiguration.getProperty("veryRandomKey");
		assertThat(actualKeyValue).isNullOrEmpty();
	}

	@Test
	public void setProperty() {
		abstractServerConfiguration.setProperty(SAMPLE_PROPERTY_KEY, SAMPLE_PROPERTY_VALUE);
		assertThat(abstractServerConfiguration.properties.containsKey(SAMPLE_PROPERTY_KEY));
		assertThat(abstractServerConfiguration.properties.containsValue(SAMPLE_PROPERTY_VALUE));
	}

	@Test
	public void setEmptyProperty() {
		abstractServerConfiguration.setProperty(SAMPLE_PROPERTY_KEY, "");
		assertThat(abstractServerConfiguration.properties.containsKey(SAMPLE_PROPERTY_KEY)).isTrue();
	}

	@Test
	public void setNullProperty() {
		abstractServerConfiguration.setProperty(SAMPLE_PROPERTY_KEY, null);
		assertThat(abstractServerConfiguration.properties.containsKey(SAMPLE_PROPERTY_KEY)).isFalse();
	}

	@Test
	public void loadProperties() {
		File propertiesFile = mock(File.class);
		abstractServerConfiguration.loadProperties(propertiesFile.getAbsolutePath());
	}

	@Test
	public void loadPropertiesWithEmptyFileParameter() {
		abstractServerConfiguration.loadProperties(null);
		assertThat(abstractServerConfiguration.properties.isEmpty()).isTrue();
	}

	@Test
	public void loadPropertiesWithException() throws Exception {
		File propertiesFile = mock(File.class);
		PowerMockito.whenNew(File.class).withArguments(PROPERTY_FILE_PATH).thenReturn(propertiesFile);
		PowerMockito.whenNew(FileInputStream.class).withArguments(propertiesFile).thenThrow(new IOException());

		abstractServerConfiguration.loadProperties(PROPERTY_FILE_PATH);
	}

	private void loadSampleConfiguration() {
		abstractServerConfiguration.setProperty(RANDOM_PROPERTY_KEY, RANDOM_PROPERTY_VALUE);
		abstractServerConfiguration.setProperty(RANDOM_PROPERTY_KEY + "2", RANDOM_PROPERTY_VALUE + "2");
	}
}