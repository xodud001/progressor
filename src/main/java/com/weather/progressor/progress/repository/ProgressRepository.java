package com.weather.progressor.progress.repository;

import com.weather.progressor.progress.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
