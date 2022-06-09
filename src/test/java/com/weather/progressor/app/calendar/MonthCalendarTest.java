package com.weather.progressor.app.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class MonthCalendarTest {

    @DisplayName("1. 캘린더 생성 테스트")
    @Test
    void test1(){
        int year = 2022;
        int month = 5;
        int dayOfMonth = 10;
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        long start = System.currentTimeMillis();
        MonthCalendar monthCalendar = MonthCalendar.create(date);
        System.out.printf("%dms\n", System.currentTimeMillis() - start);
        monthCalendar.printToCalendar();
        System.out.println();
        monthCalendar.printToList();

        Day day = monthCalendar.getDays().get(5).get(6);
        assertThat(day.getMonth()).isEqualTo(monthCalendar.getMonthValue() + 1);
        assertThat(monthCalendar.getYear()).isEqualTo(year);
        assertThat(monthCalendar.getMonthValue()).isEqualTo(month);
        assertThat(monthCalendar.getDays().size()).isEqualTo(6);
        assertThat(monthCalendar.getDays().get(0).size()).isEqualTo(7);
    }
}