package com.weather.progressor.app.progressdetail.repository;

import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import com.weather.progressor.app.progressdetail.repository.query.DetailQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressDetailRepository extends JpaRepository<ProgressDetail, Long>, DetailQueryRepository {
}
