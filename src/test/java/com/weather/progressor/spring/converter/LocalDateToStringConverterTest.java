package com.weather.progressor.spring.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateToStringConverterTest {


    @DisplayName("1. LocalDate to String")
    @Test
    void tet1(){
        LocalDateToStringConverter converter = new LocalDateToStringConverter();

        String convert = converter.convert(LocalDate.of(2022, 7, 5));

        assertThat(convert).isEqualTo("2022-07-05");
    }
}