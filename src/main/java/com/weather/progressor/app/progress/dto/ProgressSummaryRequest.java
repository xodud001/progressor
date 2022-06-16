package com.weather.progressor.app.progress.dto;

import com.weather.progressor.app.progress.domain.ProgressStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProgressSummaryRequest {

    private List<ProgressStatus> statuses;
}
