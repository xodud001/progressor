package com.weather.progressor.member.service;

import com.weather.progressor.member.MemberFactory;
import com.weather.progressor.member.domain.Member;
import com.weather.progressor.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long signUp(Member member){
        return memberRepository.save(member).getId();
    }

    public boolean signIn(Member member) {
        Member findMember = memberRepository
                .findByUsername(member.getUsername())
                .orElse(MemberFactory.empty());

        return checkPassword(member.getPassword(), findMember.getPassword());
    }

    private boolean checkPassword(String request, String target){
        return target.equals(request);
    }
}
