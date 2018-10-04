package com.example.bootdemo.util;

import org.joda.time.DateTime;

import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    public static DateTime parseLongToDate(Long millis) {
        return new DateTime(millis);
    }

    public static Date now() {
        return new DateTime().toDate();
    }

    public static DateTime nowWithTime() {
        return new DateTime();
    }

    public static long nowMillis() {
        return new DateTime().getMillis();
    }

}
