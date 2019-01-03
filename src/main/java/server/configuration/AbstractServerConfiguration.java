package server.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import common.VariableEvaluator;

public abstract class AbstractServerConfiguration {

	private Logger logger = Logger.getLogger(AbstractServerConfiguration.class.getName());

	protected Properties properties;

	public AbstractServerConfiguration() {
		properties = new Properties();
		loadConfiguration();
	}

	public String getProperty(String key) {
		String propertyValue = this.properties.getProperty(key);
		if (propertyValue != null) {
			propertyValue = propertyValue.trim();
		}
		return propertyValue;
	}

	public void setProperty(String key, String value) {
		if(key != null && value != null) {
			this.properties.setProperty(key, value);
		}
	}

	public synchronized void loadProperties(String filePaths) {
		if (!VariableEvaluator.isEmpty(filePaths)) {
			for (String filePath : filePaths.split(",")) {
				loadPropertiesFromFile(new File(filePath));
			}
		}
	}

	private void loadPropertiesFromFile(File configFile) {
		try (FileInputStream input = new FileInputStream(configFile)) {
			properties.load(input);
		} catch (IOException ioException) {
			logger.info(ioException.getMessage());
		}
	}

	protected abstract void loadConfiguration();

}
