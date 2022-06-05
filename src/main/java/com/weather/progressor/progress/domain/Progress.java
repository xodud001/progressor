package com.weather.progressor.progress.domain;


import com.weather.progressor.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long id;

    private Instant registDate;

    private int goal; // 설정한 목표 수치

    private int progress; // 진척도 현재 수치

    private String title;

    @Enumerated(STRING)
    private ProgressStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void delete() {
        this.status = ProgressStatus.DELETED;
    }

    public void modify(Progress progress) {
        this.goal = progress.getGoal();
        this.title = progress.getTitle();
    }

    public void changeStatus(ProgressStatus status) {
        this.status = status;
    }

    public static Progress of(int goal ,String title, Member member){
        return Progress.builder()
                .registDate(Instant.now())
                .goal(goal)
                .progress(0)
                .title(title)
                .member(member)
                .status(ProgressStatus.OPENED)
                .build();
    }

}
