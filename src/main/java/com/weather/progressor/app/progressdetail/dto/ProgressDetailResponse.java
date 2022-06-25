package com.weather.progressor.app.progressdetail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ProgressDetailResponse {

    private Long id;

    private String recordAt;
    private int progressSlice;

    public static ProgressDetailResponse of(ProgressDetailDto dto){
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime zonedDateTime = dto.getRecordAt().atZone(now.getZone());
        String date = String.format("%04d-%02d-%02d %02d:%02d",
                zonedDateTime.getYear(),
                zonedDateTime.getMonthValue(),
                zonedDateTime.getDayOfMonth(),
                zonedDateTime.getHour(),
                zonedDateTime.getMinute());
        return new ProgressDetailResponse(dto.getId(), date, dto.getProgressSlice());
    }
}
