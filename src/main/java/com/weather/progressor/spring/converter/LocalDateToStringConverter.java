package com.weather.progressor.spring.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate source) {
        return String.format(
                "%04d-%02d-%02d",
                source.getYear(),
                source.getMonthValue(),
                source.getDayOfMonth());
    }

}
