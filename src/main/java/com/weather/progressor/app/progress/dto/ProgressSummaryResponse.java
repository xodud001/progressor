package com.weather.progressor.app.progress.dto;

import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.ProgressDto;
import lombok.*;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProgressSummaryResponse {

    private List<ProgressStatus> statuses;
    private List<ProgressDto> progresses;

}
