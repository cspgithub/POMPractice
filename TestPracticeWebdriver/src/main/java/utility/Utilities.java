package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class Utilities {
	public String formatCurrentLocalDate() {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd"));
		return date;

	}
	public static String getDecodedString(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString.getBytes()));
	}
	

}
