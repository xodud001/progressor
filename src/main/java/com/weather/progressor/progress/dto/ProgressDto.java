package com.weather.progressor.progress.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.weather.progressor.progress.domain.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProgressDto {

    private Long id;
    private int goal;
    private int progress;
    private String title;
    private ProgressStatus status;

    @QueryProjection
    public ProgressDto(Long id, int goal, int progress, String title, ProgressStatus status) {
        this.id = id;
        this.goal = goal;
        this.progress = progress;
        this.title = title;
        this.status = status;
    }
}
