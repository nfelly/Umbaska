package uk.co.umbaska.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringToDateClass {

	public static Date getDate(String dateString1, String format) {
		return getDate(dateString1, format, Locale.ENGLISH);
	}

	public static Date getDate(String dateString1, String format, Locale locale) {
		Date date = null;

		DateFormat format1 = new SimpleDateFormat(format, locale);

		try {
			date = format1.parse(dateString1);

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return date;

	}

}