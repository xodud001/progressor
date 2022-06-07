package com.weather.progressor.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import static java.util.Calendar.*;

@Getter
@AllArgsConstructor
@Builder
public class MonthCalendar implements Calendar{

    private int year;
    private int monthValue;
    private int todayIndex;

    public List<List<Day>> days;

    public static MonthCalendar create(LocalDate date){

        List<Integer> days = new ArrayList<>(42);

        int dayOfWeekValue = getDayOfWeekValue(date.getDayOfWeek());

        fillPrevMonthDays(days, date, dayOfWeekValue);

        int todayIndex = days.size() + today().getDayOfMonth();

        int totalDaysForMonth = getTotalDaysForMonth(date);

        fillCurrentMonthDays(days, totalDaysForMonth);
        fillNextMonthDays(days);

        return new MonthCalendar(date.getYear(), date.getMonthValue(), todayIndex, toDays(days));
    }

    private static List<List<Day>> toDays(List<Integer> days) {
        int sequence = 1;
        List<List<Day>> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<Day> week = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                Integer day = days.get(i * 7 + j);
                week.add(new Day(sequence++, day));
            }
            result.add(week);
        }
        return result;
    }

    private static void fillNextMonthDays(List<Integer> days) {
        int rest = 42 - days.size();
        for(int i = 1 ; i <= rest ; i++){
            days.add(i);
        }
    }

    private static void fillCurrentMonthDays(List<Integer> days, int totalDaysForMonth) {
        for(int i = 1; i <= totalDaysForMonth; i++){
            days.add(i);
        }
    }

    private static void fillPrevMonthDays(List<Integer> days, LocalDate firstDayOfMonth, int dayOfWeekValue) {
        LocalDate prevMonthStart = firstDayOfMonth.minusDays(dayOfWeekValue);
        int totalDaysForMonth = getTotalDaysForMonth(prevMonthStart);
        for(int i = prevMonthStart.getDayOfMonth(); i <= totalDaysForMonth ; i++){
            days.add(i);
        }
    }

    private static LocalDate today() {
        Instant instant = Instant.now();
        TimeZone timeZone = getInstance().getTimeZone();

        ZonedDateTime zonedDateTime = instant.atZone(timeZone.toZoneId());
        return zonedDateTime.toLocalDate();
    }

    private static LocalDate getDate(int year, int valueOfMonth) {
        return LocalDate.of(year, valueOfMonth, 1);
    }

    private static int getDayOfWeekValue(DayOfWeek dayOfWeek) {
        return dayOfWeek.getValue() == 7 ? 0 : dayOfWeek.getValue();
    }

    private static int getTotalDaysForMonth(LocalDate date) {
        return new GregorianCalendar(date.getYear(),
                date.getMonthValue()-1,
                date.getDayOfMonth()
                )
                .getActualMaximum(DAY_OF_MONTH);
    }

    public void printWithId(){
        System.out.printf("%d년 %d월\n", getYear(), getMonthValue());

        for (List<Day> week : days) {
            for (Day day : week) {
                System.out.printf("%2d일, id=%2d    ", day.getDay(), day.getId());
            }
            System.out.println();
        }
    }

    public void print(){
        System.out.printf("%d년 %d월\n", getYear(), getMonthValue());


        for (List<Day> week : days) {
            for (Day day : week) {
                System.out.printf("%2d일    ", day.getDay());
            }
            System.out.println();
        }
    }
}
