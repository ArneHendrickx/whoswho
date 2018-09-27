package com.axxes.whoswho.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtils {

    public static LocalDateTime getFirstDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1).atStartOfDay();
    }

    public static LocalDateTime getLastDayOfMonth() {
        return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1).atTime(23,59);
    }
}
