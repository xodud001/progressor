package com.weather.progressor.member.service;

import com.weather.progressor.member.domain.Member;
import com.weather.progressor.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    private Member givenMember() {
        return Member.builder()
                .id(1L)
                .username("test")
                .password("test")
                .build();
    }

    @DisplayName("1. 회원가입")
    @Test
    void test1(){
        Member member = givenMember();

        when(memberRepository.save(any(Member.class))).then(returnsFirstArg());

        Long savedMemberId = memberService.signUp(member);

        assertThat(savedMemberId).isEqualTo(1L);
    }

    @DisplayName("2. 로그인 - 성공")
    @Test
    void test2(){
        Member member = givenMember();

        when(memberRepository.findByUsername(member.getUsername())).thenReturn(of(member));

        boolean result = memberService.signIn(member);

        assertThat(result).isTrue();
    }

    @DisplayName("3. 로그인 - 실패")
    @Test
    void test3(){
        Member member = givenMember();

        when(memberRepository.findByUsername(member.getUsername())).thenReturn(empty());

        boolean result = memberService.signIn(member);

        assertThat(result).isFalse();
    }
}