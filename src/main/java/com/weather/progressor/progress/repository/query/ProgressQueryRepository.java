package com.weather.progressor.progress.repository.query;

import com.weather.progressor.progress.dto.AllProgressResponse;
import com.weather.progressor.progress.dto.ProgressDto;

import java.time.Instant;
import java.util.List;

public interface ProgressQueryRepository {

    List<ProgressDto> findAllProgress(Long memberId);
}
