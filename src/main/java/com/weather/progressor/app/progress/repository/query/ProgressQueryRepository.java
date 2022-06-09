package com.weather.progressor.app.progress.repository.query;

import com.weather.progressor.app.progress.dto.ProgressDto;

import java.util.List;

public interface ProgressQueryRepository {

    List<ProgressDto> findAllProgress(Long memberId);
}
