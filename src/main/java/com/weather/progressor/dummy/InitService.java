package com.weather.progressor.dummy;


import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.domain.Progress;
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
        progress1.stackProgress(100);
        em.persist(progress1);

        Progress progress2 = Progress.of(1000, "윗몸 일으키기", member);
        progress2.stackProgress(600);
        em.persist(progress2);

        Progress progress3 = Progress.of(1000, "스쿼드", member);
        progress3.stackProgress(300);
        em.persist(progress3);

    }

    private Member memberInit(){
        Member member = Member.builder().username("user").password("1234").build();
        em.persist(member);
        return member;
    }
}
