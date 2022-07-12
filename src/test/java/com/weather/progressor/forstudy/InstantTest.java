package com.weather.progressor.forstudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static java.util.Calendar.getInstance;

public class InstantTest {

    @DisplayName("1. Instant.now() 확인")
    @Test
    void test1(){
        Instant instant = Instant.now();

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(9));
        System.out.println(offsetDateTime);
    }

    @DisplayName("2. Instant로 LocalDateTime 가져오기")
    @Test
    void test2(){
        Instant instant = Instant.now();
        TimeZone timeZone = getInstance().getTimeZone();

        ZonedDateTime zonedDateTime = instant.atZone(timeZone.toZoneId());

        System.out.println(zonedDateTime.getDayOfMonth());
        System.out.println(zonedDateTime.getHour());

        System.out.println(zonedDateTime.toLocalDateTime());
    }
}
