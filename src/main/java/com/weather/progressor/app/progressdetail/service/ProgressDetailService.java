package com.weather.progressor.app.progressdetail.service;

import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import com.weather.progressor.app.progressdetail.repository.ProgressDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressDetailService {

    private final ProgressDetailRepository detailRepository;

    public long createDetail(ProgressDetail detail){
        ProgressDetail savedDetail = detailRepository.save(detail);
        return savedDetail.getId();
    }


}

