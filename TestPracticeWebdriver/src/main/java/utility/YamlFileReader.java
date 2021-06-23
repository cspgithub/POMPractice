package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

import base.FrameworkConstants;
import exception.PropertyNotFoundException;

public final class YamlFileReader {// final : no other can extend this class

	private YamlFileReader() {
		// private : no can create object of this class
	}

	private static final Map<String, String> BY_VALUE = new HashMap<>();

	static {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(FrameworkConstants.getYmlfilepath()));
			Yaml yaml = new Yaml();
			Map<String, Object> data = yaml.load(inputStream);
			for (Entry<String, Object> pair : data.entrySet()) {
				BY_VALUE.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue())); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		if (Objects.isNull(key) || Objects.isNull(BY_VALUE.get(key))) {
			throw new PropertyNotFoundException(key + "  not found in yml file,pls check and resolve");
		}
		return BY_VALUE.get(key);//returns the value associated with the specified key
	}

}
