package com.weather.progressor.dummy;


import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progressdetail.domain.ProgressDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitService {

    private final EntityManager em;

    @Transactional
    public void init(){
        Member member = memberInit();
        progressInit(member);
    }

    private void progressInit(Member member) {
        Progress progress1 = Progress.of(1000, "팔굽펴혀기", member);
        progress1.stackProgress(150);
        em.persist(progress1);

        ProgressDetail detail1 = ProgressDetail.of(50, progress1);
        ProgressDetail detail2 = ProgressDetail.of(50, progress1);
        ProgressDetail detail3 = ProgressDetail.of(50, progress1);

        em.persist(detail1);
        em.persist(detail2);
        em.persist(detail3);

        Progress progress2 = Progress.of(1000, "윗몸 일으키기", member);
        progress2.stackProgress(600);
        em.persist(progress2);

        ProgressDetail detail4 = ProgressDetail.of(100, progress2);
        ProgressDetail detail5 = ProgressDetail.of(300, progress2);
        ProgressDetail detail6 = ProgressDetail.of(200, progress2);

        em.persist(detail4);
        em.persist(detail5);
        em.persist(detail6);

        Progress progress3 = Progress.of(1000, "스쿼드", member);
        progress3.stackProgress(300);
        em.persist(progress3);

        ProgressDetail detail7 = ProgressDetail.of(50, progress3);
        ProgressDetail detail8 = ProgressDetail.of(150, progress3);
        ProgressDetail detail9 = ProgressDetail.of(100, progress3);

        em.persist(detail7);
        em.persist(detail8);
        em.persist(detail9);
    }

    private Member memberInit(){
        Member member = Member.builder().username("user").password("1234").build();
        em.persist(member);
        return member;
    }
}
