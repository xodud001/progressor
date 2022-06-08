package com.weather.progressor.progress.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weather.progressor.progress.domain.QProgress;
import com.weather.progressor.progress.dto.ProgressDto;
import com.weather.progressor.progress.dto.QProgressDto;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;

public class ProgressRepositoryImpl implements ProgressQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ProgressRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProgressDto> findAllProgress(Long memberId) {
        QProgress p = QProgress.progress1;

        return queryFactory.select(new QProgressDto(
                    p.id,
                    p.goal,
                    p.progress,
                    p.title,
                    p.status
                )).from(p)
                .where(p.member.id.eq(memberId))
                .fetch();
    }
}