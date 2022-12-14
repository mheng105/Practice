package com.vmo.training.demo.configs;

import java.io.IOException;
import java.util.Properties;

public class ConfigSetting {
    private static final String BROWSER = "browser";
    private static final String DEFAULT_TIMEOUT = "timeout";

    private static final String URL = "url";
    private static final String MAIL = "email";
    private static final String PASSWORD = "password";

    private Properties configProperties;
    private static String PROPERTIESFILENAME = "config";

    public ConfigSetting(String projectDirLocation) {
        try {
            setConfigProperties(PropertySettingStoreUtil.getSettings(projectDirLocation, PROPERTIESFILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConfigProperties(Properties settings) {
        this.configProperties = settings;
    }

    public String getUrl() {
        return this.configProperties.getProperty(URL);
    }

    public String getBrowser() {
        return this.configProperties.getProperty(BROWSER);
    }

    public String getDefaultTimeout() {
        return this.configProperties.getProperty(DEFAULT_TIMEOUT);
    }

    public String getMail() {
        return this.configProperties.getProperty(MAIL);
    }

    public String getPassword() {
        return this.configProperties.getProperty(PASSWORD);
    }

    public Properties getConfigProperties() {
        if (configProperties == null) {
            configProperties = new Properties();
        }
        return configProperties;
    }
}
