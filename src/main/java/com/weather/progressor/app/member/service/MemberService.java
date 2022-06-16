package com.weather.progressor.app.member.service;

import com.weather.progressor.app.member.MemberFactory;
import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long signUp(Member member){
        return memberRepository.save(member).getId();
    }

    public Member signIn(Member member) {
        Member findMember = memberRepository
                .findByUsername(member.getUsername())
                .orElse(MemberFactory.empty());

        if(checkPassword(member.getPassword(), findMember.getPassword())) {
            return findMember;
        }else{
            return null;
        }
    }

    public Member findMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(username + "에 해당하는 멤버를 찾지 못했습니다."));
    }

    private boolean checkPassword(String request, String target){
        return target.equals(request);
    }
}
