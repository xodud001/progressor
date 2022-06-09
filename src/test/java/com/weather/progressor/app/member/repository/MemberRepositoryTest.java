package com.weather.progressor.app.member.repository;

import com.weather.progressor.app.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    private Member givenMember() {
        return Member.builder()
                .username("test")
                .password("test")
                .build();
    }

    @DisplayName("1. username 으로 멤버 찾기")
    @Test
    void test1(){
        Member member = givenMember();
        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findByUsername(member.getUsername()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());

    }
}