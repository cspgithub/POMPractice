package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
	public String formatCurrentLocalDate() {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd"));
		return date;

	}

}
