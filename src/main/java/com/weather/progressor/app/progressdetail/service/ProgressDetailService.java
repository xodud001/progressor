package com.weather.progressor.app.progressdetail.service;

import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import com.weather.progressor.app.progressdetail.dto.CreateDetailForm;
import com.weather.progressor.app.progressdetail.repository.ProgressDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressDetailService {

    private final ProgressService progressService;
    private final ProgressDetailRepository detailRepository;

    public long createDetail(CreateDetailForm form){
        Progress progress = progressService.getProgress(form.getProgressId());
        ProgressDetail detail = ProgressDetail.of(form.getFigure(), progress);
        ProgressDetail savedDetail = detailRepository.save(detail);
        progress.stackProgress(savedDetail.getProgressSlice());
        return savedDetail.getId();
    }


}

