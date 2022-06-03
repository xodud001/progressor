package com.weather.progressor.progress.service;

import com.weather.progressor.progress.domain.Progress;
import com.weather.progressor.progress.domain.ProgressStatus;
import com.weather.progressor.progress.exception.ProgressNotFountException;
import com.weather.progressor.progress.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public long openProgress(Progress progress){
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

}
