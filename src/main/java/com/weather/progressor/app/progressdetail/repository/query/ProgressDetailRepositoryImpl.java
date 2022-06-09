package com.weather.progressor.app.progressdetail.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weather.progressor.app.progressdetail.domain.QProgressDetail;
import com.weather.progressor.app.progressdetail.dto.ProgressDetailDto;
import com.weather.progressor.app.progressdetail.dto.QProgressDetailDto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProgressDetailRepositoryImpl implements DetailQueryRepository{


    private final JPAQueryFactory queryFactory;

    public ProgressDetailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProgressDetailDto> allDetail(Long progressId) {
        QProgressDetail pd = QProgressDetail.progressDetail;

        return queryFactory.select(
                        new QProgressDetailDto(
                                pd.id,
                                pd.recordAt,
                                pd.progressSlice
                        )
                ).from(pd)
                .where(pd.progress.id.eq(progressId))
                .fetch();
    }
}
