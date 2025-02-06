import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TestCalendar {
    public static void main(String[] args) {
        // Construct a Gregorian calendar for the current date and time
        Calendar calendar = new GregorianCalendar();
        System.out.println("CURRENT time is: " + new Date());
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("HOUR OF DAY" + calendar.get(Calendar.HOUR_OF_DAY));
        System.err.println("MINUTE: " + calendar.get(Calendar.MINUTE) );
        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("WEE_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("AM:PM: " + calendar.get(Calendar.AM_PM));

        // Construct a calendar for December 25, 1997
        Calendar calendar1 = new GregorianCalendar(1997, 11, 25);
        String[] dayNameOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println("December 25, 1997 is a " + dayNameOfWeek[calendar1.get(Calendar.DAY_OF_WEEK) -1]);

        Calendar c = new GregorianCalendar();

        System.out.println("MONTH: " + c.get(Calendar.MONTH));

    }
}
