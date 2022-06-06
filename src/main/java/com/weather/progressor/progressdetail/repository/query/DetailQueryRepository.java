package com.weather.progressor.progressdetail.repository.query;

import com.weather.progressor.progressdetail.dto.ProgressDetailDto;

import java.util.List;

public interface DetailQueryRepository {

    List<ProgressDetailDto> allDetail(Long progressId);
}
