package com.weather.progressor.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtil{

    public static LocalDate today(){
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        ZonedDateTime zonedDateTime = Instant.now().atZone(timeZone.toZoneId());

        return zonedDateTime.toLocalDate();
    }
}
