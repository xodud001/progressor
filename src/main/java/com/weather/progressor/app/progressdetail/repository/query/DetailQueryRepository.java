package com.weather.progressor.app.progressdetail.repository.query;

import com.weather.progressor.app.progressdetail.dto.ProgressDetailDto;

import java.util.List;

public interface DetailQueryRepository {

    List<ProgressDetailDto> allDetail(Long progressId);
}
