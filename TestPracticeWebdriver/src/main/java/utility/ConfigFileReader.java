package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import base.FrameworkConstants;

public final class ConfigFileReader {// final : no other can extend this class

	private ConfigFileReader() {
		// private : no can create object of this class
	}

	private static final Map<String, String> BY_VALUE = new HashMap<>();
	private static Properties property = new Properties();

	static {// store all config values in cache

		FileInputStream file;
		try {
			file = new FileInputStream(FrameworkConstants.getConfigfilerpath());
			property.load(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Map.Entry<Object, Object> entry : property.entrySet()) {
			BY_VALUE.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));

		}

	}

	public static String getValue(String value) {
		if (Objects.isNull(value) || Objects.isNull(BY_VALUE.get(value))) {
			
		}
		return BY_VALUE.get(value);
	}

}
