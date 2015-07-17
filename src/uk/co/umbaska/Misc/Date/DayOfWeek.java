package uk.co.umbaska.Misc.Date;

/**
 * Created by Zach on 17/07/15.
 */
public enum DayOfWeek {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

	public static DayOfWeek getDay(int it){
		if (it == 1){
			return MONDAY;
		}
		if (it == 2){
			return TUESDAY;
		}
		if (it == 3){
			return WEDNESDAY;
		}
		if (it == 4){
			return THURSDAY;
		}
		if (it == 5){
			return FRIDAY;
		}
		if (it == 6){
			return SATURDAY;
		}
		if (it == 7){
			return SUNDAY;
		}
		return MONDAY;
	}
}
