package com.weather.progressor.app.progress.repository;

import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.repository.query.ProgressQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long>, ProgressQueryRepository {
}
