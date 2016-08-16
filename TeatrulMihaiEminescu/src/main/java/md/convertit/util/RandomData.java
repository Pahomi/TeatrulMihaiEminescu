package md.convertit.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class RandomData {

	public static Date getrandomDate() {

		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2010);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		return gc.getTime();
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

}
