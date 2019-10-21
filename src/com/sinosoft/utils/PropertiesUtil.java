package com.sinosoft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static final String FILE_PATH = "config/es-server.properties";
	private static Properties properties;
	static {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(FILE_PATH)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}
