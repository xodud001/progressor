package com.weather.progressor.app.progressdetail.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ProgressDetailDto {

    private Long id;
    private Instant recordAt;
    private int progressSlice;

    @QueryProjection
    public ProgressDetailDto(Long id, Instant recordAt, int progressSlice) {
        this.id = id;
        this.recordAt = recordAt;
        this.progressSlice = progressSlice;
    }

    public static ProgressDetailDto of(ProgressDetail progressDetail) {
        return new ProgressDetailDto(progressDetail.getId(),
                progressDetail.getRecordAt(),
                progressDetail.getProgressSlice());
    }
}
