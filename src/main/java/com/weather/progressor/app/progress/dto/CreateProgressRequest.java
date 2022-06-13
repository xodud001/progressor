package com.weather.progressor.app.progress.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateProgressRequest {

    private String subject;
    private Integer goalFigure;
}
