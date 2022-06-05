package com.weather.progressor.progress.dto;

import com.weather.progressor.progress.domain.ProgressStatus;
import com.weather.progressor.progressdetail.dto.ProgressDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class AllProgressResponse {

    private Long id;
    private int goal;
    private int progress;
    private String title;
    private float percent;
    private ProgressStatus status;

    private List<ProgressDetailResponse> details;
}
