package com.weather.progressor.app.member;

import com.weather.progressor.app.member.domain.Member;

public class MemberFactory {

    public static Member empty(){
        return Member.builder()
                .username("")
                .password("")
                .build();
    }
}
