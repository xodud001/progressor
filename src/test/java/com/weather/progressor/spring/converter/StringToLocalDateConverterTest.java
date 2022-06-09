package com.weather.progressor.spring.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StringToLocalDateConverterTest {

    @DisplayName("1. String to LocalDate")
    @Test
    void tet1(){
        StringToLocalDateConverter converter = new StringToLocalDateConverter();

        LocalDate date = converter.convert("2022-06");

        assertThat(date.getYear()).isEqualTo(2022);
        assertThat(date.getMonthValue()).isEqualTo(6);
    }

}