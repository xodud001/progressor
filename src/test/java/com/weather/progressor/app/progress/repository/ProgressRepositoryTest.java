package com.weather.progressor.app.progress.repository;

import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.ProgressDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ProgressRepositoryTest {

    @Autowired
    ProgressRepository progressRepository;

    @PersistenceContext
    EntityManager em;

    private Member givenMember() {
        return Member.builder()
                .username("test")
                .password("test")
                .build();
    }

    Progress givenProgress(Member member){
        return Progress.of(
                100,
                "테스트 목적",
                member);
    }


    @DisplayName("1. 모든 범위의 진척도 조회")
    @Test
    void test1(){

        Member member = givenMember();
        em.persist(member);

        em.persist(givenProgress(member));
        em.persist(givenProgress(member));
        em.persist(givenProgress(member));

        em.flush();
        em.clear();

        List<ProgressDto> allProgress = progressRepository.findAllProgress(member.getId(), List.of(ProgressStatus.OPENED));

        assertThat(allProgress.size()).isEqualTo(3);
    }
}