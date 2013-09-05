package com.dnstechpack.installer.settings;

import com.dnstechpack.installer.Installer;

import java.io.IOException;
import java.util.Properties;


/**
 * @Author ShadowChild.
 */
public class Settings {

    private Properties properties;

    public Settings() {

        properties = new Properties();
        try {

            properties.load(Installer.class.getClassLoader().getResourceAsStream("internal.properties"));
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public String getMCVersion() {

        return properties.getProperty("MCVersion");
    }
    
    public String getDNSVersion() {

    	return properties.getProperty("DNSVersion");
    }

    public String getJarVersion() {

        return getMCVersion() + "-DNS" + getDNSVersion();
    }
}
