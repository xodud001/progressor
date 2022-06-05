package com.weather.progressor.progress.repository;

import com.weather.progressor.progress.domain.Progress;
import com.weather.progressor.progress.repository.query.ProgressQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long>, ProgressQueryRepository {
}
