package com.weather.progressor.app.progress.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProgressDto {

    private Long id;
    private int goal;
    private int progress;
    private String object;
    private ProgressStatus status;

    @QueryProjection
    public ProgressDto(Long id, int goal, int progress, String object, ProgressStatus status) {
        this.id = id;
        this.goal = goal;
        this.progress = progress;
        this.object = object;
        this.status = status;
    }
}
