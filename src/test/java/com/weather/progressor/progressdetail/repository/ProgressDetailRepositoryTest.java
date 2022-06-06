package com.weather.progressor.progressdetail.repository;

import com.weather.progressor.member.domain.Member;
import com.weather.progressor.progress.domain.Progress;
import com.weather.progressor.progressdetail.domain.ProgressDetail;
import com.weather.progressor.progressdetail.dto.ProgressDetailDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProgressDetailRepositoryTest {

    @Autowired
    ProgressDetailRepository detailRepository;

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

    ProgressDetail givenProgressDetail(Progress progress) {
        return ProgressDetail.of(10, progress);
    }

    @DisplayName("1. 특정 프로그레스 기준으로 Detail 목록 가져오기")
    @Test
    void test1(){

        Member member = givenMember();
        em.persist(member);

        Progress progress = givenProgress(member);
        em.persist(progress);

        em.persist(givenProgressDetail(progress));
        em.persist(givenProgressDetail(progress));
        em.persist(givenProgressDetail(progress));

        List<ProgressDetailDto> detailDtos = detailRepository.allDetail(progress.getId());

        assertThat(detailDtos.size()).isEqualTo(3);
    }
}