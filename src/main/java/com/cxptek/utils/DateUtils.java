package com.cxptek.utils;

import com.cxptek.constant.Constant;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateUtils {
    private DateUtils() {}

    public static long getDeadline(Duration duration) {
        var now = LocalDateTime.now();
        var deadlineDt = now.plus(duration);
        return Timestamp.valueOf(deadlineDt).getTime();
    }

    public static Date convertStringToDate(String dateString, String pattern) {
        try {
            if (dateString == null) {
                return null;
            }
            if (pattern == null) {
                pattern = Constant.PATTERN_DATE_YYYMMDD_HHMMSS;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(dateString);
        } catch (Exception e) {
            log.error("convertStringToDate ===> {}", dateString + " - " + e.getMessage());
            return null;
        }
    }

    public static String convertDateToString(Date date) {
        return convertDateToString(date, Constant.PATTERN_DATE_YYYMMDD_HHMMSS);
    }

    public static String convertDateToString(Date date, String pattern) {
        try {
            if (pattern == null) {
                pattern = Constant.PATTERN_DATE_YYYMMDD_HHMMSS;
            }
            DateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } catch (Exception e) {
            log.error("convertDateToString ===> {}", e.getMessage());
            return null;
        }
    }

    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static Date convertStringToDateHaveTimeZone(String dateString, String pattern) {
        try {
            if (dateString == null) {
                return null;
            }
            if (pattern == null) {
                pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
            }
            dateString = dateString.replace(" ", "+");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
            return Date.from(zonedDateTime.toInstant());
        } catch (Exception e) {
            log.error("convertStringToDateHaveTimeZone ===> {}", dateString + " - " + e.getMessage());
            return null;
        }
    }
}

