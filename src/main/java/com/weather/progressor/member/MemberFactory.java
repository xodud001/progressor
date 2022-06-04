package com.weather.progressor.member;

import com.weather.progressor.member.domain.Member;

public class MemberFactory {

    public static Member empty(){
        return Member.builder()
                .username("")
                .password("")
                .build();
    }
}
