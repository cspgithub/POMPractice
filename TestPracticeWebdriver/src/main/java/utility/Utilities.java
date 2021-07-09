package utility;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import exception.PropertyNotFoundException;

public class Utilities {

	public static String formatCurrentLocalDate() {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd"));
		return date;

	}

	public static String getDecodedString(String data) {
		return new String(Base64.getDecoder().decode(Tokenizer(data).getBytes()));
	}

	public static String getDecoded(String data) {
		return new String(Base64.getDecoder().decode(data.getBytes()));
	}

	public static String Tokenizer(String data) {

		if (!data.contains("config:")) {
			throw new PropertyNotFoundException(data + "  not a valid data");
		}
		Map<String, String> map = new HashMap<String, String>();
		String[] keyValue = data.split(":");
		if (keyValue.length <= 1) {
			throw new PropertyNotFoundException(data + "  not a valid data");
		}
		map.put(keyValue[0], keyValue[1]);
		return ConfigFileReader.getValue(map.get("config"));
	}
}
