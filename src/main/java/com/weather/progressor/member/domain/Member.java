package com.weather.progressor.member.domain;

import com.weather.progressor.member.dto.FormSignInRequest;
import com.weather.progressor.member.dto.FormSignUpRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public static Member of(FormSignInRequest form) {
        return Member.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
    }

    public static Member of(FormSignUpRequest form) {
        return Member.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
    }
}
