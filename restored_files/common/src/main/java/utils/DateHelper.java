package utils;

import model.TimeRange;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    private static Logger log = Logger.getLogger(DateHelper.class);
    private static Instant start;

    public static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    public static String dateToFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    public static Date getDateFromUnixTimestamp(long timeStamp) {
        return new Date(timeStamp * 1000);
    }

    /**
     * Checks if param date is already passed in comparision to now
     *
     * @param deadline this date will be verified against now
     * @return True if deadline occurred, False otherwise
     */
    public static boolean isTimeout(Date deadline) {
        return new Date().compareTo(deadline) > 0;
    }

    /**
     * Thread sleep for the specified number of secs.
     *
     * @param secs Seconds to sleep.
     */
    public static void waitTime(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            log.debug("Thread sleep was interrupted");
        }
    }

    /**
     * Helps to receive Date with the passed shift in secs.
     *
     * @param shiftInSecs shift to the current date in secs.
     * @return Date with applied time shift in secs.
     */
    public static Date getDateWithShift(int shiftInSecs) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.SECOND, shiftInSecs);
        return date.getTime();
    }

    public static void setStartTime() {
        start = Instant.now();
    }

    public static long getDuration() {
        Instant stop = Instant.now();
        Duration dur = Duration.between(start, stop);

        return dur.getSeconds();
    }

    public static TimeRange stringToTimeRange(String eventTime) {
        TimeRange timeRange = new TimeRange();
        Calendar calendar = Calendar.getInstance();
        switch (eventTime) {
            case "LAST_MONTH":
                timeRange.setEndDate(calendar.getTime());
                calendar.add(Calendar.MONTH, -1);
                timeRange.setStartDate(calendar.getTime());
                break;
            default:
                throw new AssertionError("Unknown eventTime value:" + eventTime);
        }
        return timeRange;
    }

    public static boolean inRange(Date testDate, TimeRange timeRange) {
        return testDate.after(timeRange.getStartDate()) && testDate.before(timeRange.getEndDate());
    }
}
