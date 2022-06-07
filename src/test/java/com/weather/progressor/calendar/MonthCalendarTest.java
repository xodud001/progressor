package com.weather.progressor.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class MonthCalendarTest {

    @DisplayName("1. 캘린더 생성 테스트")
    @Test
    void test1(){
        LocalDate date = LocalDate.of(2022, 6, 1);
        long start = System.currentTimeMillis();
        MonthCalendar monthCalendar = MonthCalendar.create(date);
        System.out.printf("%dms\n", System.currentTimeMillis() - start);
        monthCalendar.printWithId();

        System.out.println(monthCalendar.getTodayIndex());
        assertThat(monthCalendar.getYear()).isEqualTo(2022);
        assertThat(monthCalendar.getMonthValue()).isEqualTo(6);
        assertThat(monthCalendar.getDays().size()).isEqualTo(6);
        assertThat(monthCalendar.getDays().get(0).size()).isEqualTo(7);
    }
}