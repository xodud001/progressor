package com.weather.progressor.app.progress.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProgressDto {

    private Long id;
    private int goal;
    private int progress;
    private String subject;
    private ProgressStatus status;
    private int percent;

    @QueryProjection
    public ProgressDto(Long id, int goal, int progress, String subject, ProgressStatus status) {
        this.id = id;
        this.goal = goal;
        this.progress = progress;
        this.subject = subject;
        this.status = status;
        this.percent = calPercent(goal, progress);
    }

    private static int calPercent(double goal, double progress) {
        return (int)(progress / goal * 100);
    }

    public static ProgressDto of(Progress progress) {
        return ProgressDto.builder()
                .id(progress.getId())
                .goal(progress.getGoal())
                .progress(progress.getProgress())
                .subject(progress.getSubject())
                .status(progress.getStatus())
                .percent(calPercent(progress.getGoal(), progress.getProgress()))
                .build();
    }
}
