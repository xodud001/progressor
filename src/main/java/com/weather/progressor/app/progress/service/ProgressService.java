package com.weather.progressor.app.progress.service;

import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.CreateProgressRequest;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.exception.ProgressNotFountException;
import com.weather.progressor.app.progress.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressService {

    private final ProgressRepository progressRepository;

    @Transactional(readOnly = true)
    public Progress getProgress(Long id){
        return progressRepository.findById(id)
                .orElseThrow( () -> new ProgressNotFountException(id) );
    }

    public long openProgress(CreateProgressRequest request){
        Progress progress = Progress.of(request, Member.builder().username("EMTPY").password("EMPTY").build());
        Progress savedProgress = progressRepository.save(progress);
        return savedProgress.getId();
    }

    public void deleteProgress(Progress progress){
        progress.delete();
    }

    public void modifyProgress(Long id, Progress progress){
        Progress prevProgress = getProgress(id);

        prevProgress.modify(progress);
    }

    public void changeStatus(Long id, ProgressStatus status){
        Progress progress = getProgress(id);

        progress.changeStatus(status);
    }

    @Transactional(readOnly = true)
    public List<ProgressDto> allProgress(Long memberId){
        return progressRepository.findAllProgress(memberId);
    }
}
