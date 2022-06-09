package com.weather.progressor.app.progressdetail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Builder
public class ProgressDetailResponse {

    private Long id;

    private Instant recordAt;
    private int progressSlice;
}
