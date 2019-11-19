package com.example.streetbellpos.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateHelper {
    private static final String TZ_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String TZ_DATE_MILLIS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DOB_FORMAT = "yyyy-MM-dd";
    private static final String READABLE_DATE_FORMAT = "MMM d, yyyy";
    private static final String DATE_APPM_TIME_FORMAT = "MMM dd, yyy hh:mm aa";
    private static final String AMPM_TIME_FORMAT = "hh:mm aa";
    private static final String MINUTES_FORMAT = "mm";
    private static final String MONTH_DATE_FORMAT = "MMM - dd";
    private static final String ONLY_DATE_FORMAT = "dd";
    private static final String MONTH_FORMAT = "MMM";
    private static final String DATE_STRING_FORMAT = "MMM dd, yyy";
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getDateString(Date date) {
        SimpleDateFormat dateStringFormat = new SimpleDateFormat(
                DATE_STRING_FORMAT, Locale.getDefault());
        return dateStringFormat.format(date);
    }


    public static Calendar getCalFromTZ(String metaDataDateString, boolean containMillis) {
        Calendar calendar = Calendar.getInstance();
        String format = TZ_DATE_FORMAT;
        if (containMillis)
            format = TZ_DATE_MILLIS_FORMAT;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(metaDataDateString);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getReadableDOBDate(Calendar calendar) {
        // to create user readable time
        SimpleDateFormat simpleFormat = new SimpleDateFormat(READABLE_DATE_FORMAT, Locale.getDefault());
        return simpleFormat.format(calendar.getTime());
    }

    public static String getMonthDateDOBDate(Calendar calendar) {
        // to create user readable time
        SimpleDateFormat simpleFormat = new SimpleDateFormat(MONTH_DATE_FORMAT, Locale.getDefault());
        return simpleFormat.format(calendar.getTime());
    }

    public static String getServerDob(Calendar calendar) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(DOB_FORMAT, Locale.getDefault());
        return simpleFormat.format(calendar.getTime());
    }

    public static Calendar getCalendarFromServerDob(String birthday) {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleFormat = new SimpleDateFormat(DOB_FORMAT, Locale.getDefault());
        try {
            Date date = simpleFormat.parse(birthday);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static String getMinutesFromTZ(String timeStr) {
        try {
            Calendar cal = Calendar.getInstance();
            // Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    TZ_DATE_FORMAT, Locale.getDefault());
//            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(timeStr);
            cal.setTime(date);
            SimpleDateFormat timeFormat = new SimpleDateFormat(
                    MINUTES_FORMAT, Locale.getDefault());
            return timeFormat.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMonth(Date date) {
        SimpleDateFormat monthFormat = new SimpleDateFormat(
                MONTH_FORMAT, Locale.getDefault());
        return monthFormat.format(date);
    }

    public static String getDifferenceFromTZ(String timeStr) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    TZ_DATE_FORMAT, Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(timeStr);
            cal.setTime(date);

            Calendar currentUtcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            long diffInMillis = currentUtcCal.getTimeInMillis() - cal.getTimeInMillis();

            if (diffInMillis > 0) {
                return getRelevantString(diffInMillis);
            } else {
                return "";
            }


        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    //TODO - fix time values
    private static String getRelevantString(long diff) {

        if (diff < MINUTE_MILLIS) {
            return "0m";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1m";
        } else if (diff < 3 * MINUTE_MILLIS) {
            return "2m";
        } else if (diff < 4 * MINUTE_MILLIS) {
            return "3m";
        } else if (diff < 5 * MINUTE_MILLIS) {
            return "4m";
        } else if (diff < 6 * MINUTE_MILLIS) {
            return "5m";
        } else if (diff < 11 * MINUTE_MILLIS) {
            return "6m";
        } else if (diff < 16 * MINUTE_MILLIS) {
            return "15m";
        } else if (diff < 31 * MINUTE_MILLIS) {
            return "20m";
        } else if (diff < 61 * MINUTE_MILLIS) {
            return "1h";
        } else if (diff < 121 * MINUTE_MILLIS) {
            return "2h";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + "h";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "1d";
        } else if (diff < 3 * DAY_MILLIS) {
            return "2d";
        } else if (diff < 4 * DAY_MILLIS) {
            return "3d";
        } else if (diff < 5 * DAY_MILLIS) {
            return "4d";
        } else if (diff < 6 * DAY_MILLIS) {
            return "5d";
        } else if (diff < 7 * DAY_MILLIS) {
            return "6d";
        } else if (diff < 8 * DAY_MILLIS) {
            return "1w";
        } else {
            return "2w";
        }
    }

    public static String getOnlyDate(Calendar calendar) {
        SimpleDateFormat onlyDateFormat = new SimpleDateFormat(
                ONLY_DATE_FORMAT, Locale.getDefault());
        return onlyDateFormat.format(calendar.getTime());
    }

    public static long getEpochTime() {
        long millis = System.currentTimeMillis();
        return millis / 1000;
    }

    public static String getTimeFromEpoch(String dateEpoch) {
        if (dateEpoch != null && !dateEpoch.isEmpty()) {
            double dateDoubEp = Double.parseDouble(dateEpoch);
            long dateEp = (long) dateDoubEp;
            Calendar calendar = getDateFromEpoch(dateEp);
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    AMPM_TIME_FORMAT, Locale.getDefault());
            return dateFormat.format(calendar.getTime());
        } else
            return "";
    }


    public static String getDayTimeFromEpoch(String dateEpoch) {
        if (dateEpoch != null && !dateEpoch.isEmpty()) {
            double dateDoubEp = Double.parseDouble(dateEpoch);
            long dateEp = (long) dateDoubEp;
            Calendar calendar = getDateFromEpoch(dateEp);
            Calendar today = Calendar.getInstance();
            SimpleDateFormat dateFormat = null;
            if (today.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR) && today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
                dateFormat = new SimpleDateFormat(
                        AMPM_TIME_FORMAT, Locale.getDefault());
            else
                dateFormat = new SimpleDateFormat(
                        DATE_APPM_TIME_FORMAT, Locale.getDefault());
            return dateFormat.format(calendar.getTime());
        } else
            return "";
    }


    public static Calendar getDateFromEpoch(long epochTime) {
        Date date = new Date(epochTime * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static boolean isSameYear(Calendar now, Calendar calendar) {
        return now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR);
    }

    public static boolean isSameMonth(Calendar now, Calendar calendar) {
        return (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) && (now.get(Calendar.MONTH) == calendar.get(Calendar.MONTH));
    }

    public static boolean isSameDay(Calendar now, Calendar calendar) {
        return now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && now.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
    }


}
