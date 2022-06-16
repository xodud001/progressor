package com.weather.progressor.app.progress.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProgressRequest {

    private String subject;
    private Integer goalFigure;
}
