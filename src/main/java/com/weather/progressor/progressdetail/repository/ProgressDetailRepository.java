package com.weather.progressor.progressdetail.repository;

import com.weather.progressor.progressdetail.domain.ProgressDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressDetailRepository extends JpaRepository<ProgressDetail, Long> {
}