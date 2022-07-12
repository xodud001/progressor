package com.weather.progressor.app.progressdetail.service;

import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import com.weather.progressor.app.progressdetail.dto.CreateDetailForm;
import com.weather.progressor.app.progressdetail.dto.EditDetailForm;
import com.weather.progressor.app.progressdetail.dto.ProgressDetailDto;
import com.weather.progressor.app.progressdetail.dto.ProgressDetailResponse;
import com.weather.progressor.app.progressdetail.repository.ProgressDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    public List<ProgressDetailDto> getAllDetails(Long progressId) {
        return detailRepository.allDetail(progressId);
    }

    public ProgressDetailDto findById(Long id) {
        ProgressDetail progressDetail = detailRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("진척도의 상세정보를 찾을 수 없습니다. id=" + id));
        return ProgressDetailDto.of(progressDetail);
    }

    public void updateDetail(Long id, EditDetailForm form) {
        ProgressDetail progressDetail = detailRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("진척도의 상세정보를 찾을 수 없습니다. id=" + id));

        int progressSlice = progressDetail.getProgressSlice();
        progressDetail.updateSlice(form.getProgressSlice());

        progressDetail.getProgress().stackProgress(form.getProgressSlice() -progressSlice);
    }
}

