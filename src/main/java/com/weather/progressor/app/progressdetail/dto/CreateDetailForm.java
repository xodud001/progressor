package com.weather.progressor.app.progressdetail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetailForm {

    private Long progressId;
    private Integer figure;

}
