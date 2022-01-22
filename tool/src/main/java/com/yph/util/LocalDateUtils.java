package com.yph.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Date;

public class LocalDateUtils {

    private static final ThreadLocal<DateTimeFormatter> TIMESTAMP = new ThreadLocal<DateTimeFormatter>(){
        @Override
        public DateTimeFormatter get() {
            return DateTimeFormatter.ofPattern(FORMAT_TIMESTAMP);
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DATETIME = new ThreadLocal<DateTimeFormatter>(){
        @Override
        public DateTimeFormatter get() {
            return DateTimeFormatter.ofPattern(FORMAT_DATETIME);
        }
    };

    private static final ThreadLocal<DateTimeFormatter> DATE = new ThreadLocal<DateTimeFormatter>(){
        @Override
        public DateTimeFormatter get() {
            return DateTimeFormatter.ofPattern(FORMAT_DATE);
        }
    };

    private static final ThreadLocal<DateTimeFormatter> TIME = new ThreadLocal<DateTimeFormatter>(){
        @Override
        public DateTimeFormatter get() {
            return DateTimeFormatter.ofPattern(FORMAT_TIME);
        }
    };

    private static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private static final String FORMAT_TIME = "HH:mm";

    public static String toTimestampString(TemporalAccessor dateTime) {
        return TIMESTAMP.get().format(dateTime);
    }

    public static String toDatetimeString(TemporalAccessor dateTime) {
        return DATETIME.get().format(dateTime);
    }

    public static String toDateString(TemporalAccessor dateTime) {
        return DATE.get().format(dateTime);
    }

    public static String toTimeString(TemporalAccessor dateTime) {
        return TIME.get().format(dateTime);
    }

    public static LocalDateTime parseTimestamp(String dateTime) {
        return TIMESTAMP.get().parse(dateTime, new TemporalQuery<LocalDateTime>() {
            @Override
            public LocalDateTime queryFrom(TemporalAccessor temporal) {
                return LocalDateTime.from(temporal);
            }
        });
    }

    public static LocalDateTime parseDatetime(String dateTime) {
        return TIMESTAMP.get().parse(dateTime, new TemporalQuery<LocalDateTime>() {
            @Override
            public LocalDateTime queryFrom(TemporalAccessor temporal) {
                return LocalDateTime.from(temporal);
            }
        });
    }

    public static LocalDate parseDate(String dateTime) {
        return DATE.get().parse(dateTime, new TemporalQuery<LocalDate>() {
            @Override
            public LocalDate queryFrom(TemporalAccessor temporal) {
                return LocalDate.from(temporal);
            }
        });
    }

    public static LocalTime parseTime(String dateTime) {
        return TIME.get().parse(dateTime, new TemporalQuery<LocalTime>() {
            @Override
            public LocalTime queryFrom(TemporalAccessor temporal) {
                return LocalTime.from(temporal);
            }
        });
    }

    public static LocalDateTime convertDatetime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static LocalDate convertDate(Date date) {
        return convertDatetime(date).toLocalDate();
    }

    public static LocalTime convertTime(Date date) {
        return convertDatetime(date).toLocalTime();
    }

}
