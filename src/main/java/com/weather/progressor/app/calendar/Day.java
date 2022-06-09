package com.weather.progressor.app.calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Day {

    private int year;
    private int month;
    private int day;
    private boolean isToday;
}
