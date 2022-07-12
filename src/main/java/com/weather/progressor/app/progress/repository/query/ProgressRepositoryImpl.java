package com.weather.progressor.app.progress.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.domain.QProgress;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.dto.QProgressDto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProgressRepositoryImpl implements ProgressQueryRepository{

    private final JPAQueryFactory queryFactory;

    public ProgressRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProgressDto> findAllProgress(Long memberId, List<ProgressStatus> statuses) {
        QProgress p = QProgress.progress1;

        return queryFactory.select(new QProgressDto(
                    p.id,
                    p.goal,
                    p.progress,
                    p.subject,
                    p.status
                )).from(p)
                .where(p.member.id.eq(memberId), p.status.in(statuses))
                .fetch();
    }
}
