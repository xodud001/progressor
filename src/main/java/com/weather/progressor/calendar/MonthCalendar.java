package com.weather.progressor.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.*;

@Getter
@AllArgsConstructor
@Builder
public class MonthCalendar implements Calendar{

    private int year;
    private int monthValue;

    public List<List<Day>> days;

    public static MonthCalendar create(LocalDate date){

        List<Day> days = new ArrayList<>(42);

        int dayOfWeekValue = getDayOfWeekValue(date.getDayOfWeek());

        fillPrevMonthDays(days, date, dayOfWeekValue);
        fillCurrentMonthDays(days, date);
        fillNextMonthDays(days, date);

        return new MonthCalendar(date.getYear(), date.getMonthValue(), toDays(days));
    }

    private static List<List<Day>> toDays(List<Day> days) {
        List<List<Day>> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<Day> week = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                week.add(days.get(i * 7 + j));
            }
            result.add(week);
        }
        return result;
    }

    private static void fillPrevMonthDays(List<Day> days, LocalDate date, int dayOfWeekValue) {
        LocalDate prevMonthStart = date.minusDays(dayOfWeekValue);
        int totalDaysForMonth = getTotalDaysForMonth(prevMonthStart);
        for(int i = prevMonthStart.getDayOfMonth(); i <= totalDaysForMonth ; i++){
            Day day = new Day(prevMonthStart.getYear(), prevMonthStart.getMonthValue(), i);
            days.add(day);
        }
    }

    private static void fillCurrentMonthDays(List<Day> days, LocalDate date) {
        int totalDaysForMonth = getTotalDaysForMonth(date);

        for(int i = 1; i <= totalDaysForMonth; i++){
            Day day = new Day(date.getYear(), date.getMonthValue(), i);
            days.add(day);
        }
    }

    private static void fillNextMonthDays(List<Day> days, LocalDate date) {
        int rest = 42 - days.size();
        for(int i = 1 ; i <= rest ; i++){
            Day day = new Day(date.getYear(), date.getMonthValue()+1, i);
            days.add(day);
        }
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

    public void printToCalendar(){
        System.out.printf("%d년 %d월\n", getYear(), getMonthValue());


        for (List<Day> week : days) {
            for (Day day : week) {
                System.out.printf("%2d일    ", day.getDay());
            }
            System.out.println();
        }
    }

    public void printToList(){
        for (List<Day> week : days) {
            for (Day day : week) {
                System.out.printf("%4d년 %2d월 %2d일\n", day.getYear(), day.getMonth(), day.getDay());
            }
        }
    }
}
