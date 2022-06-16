package com.weather.progressor.app.progress.domain;


import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.dto.CreateProgressRequest;
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

    private int figure; // 설정한 목표 수치

    private int progress; // 진척도 현재 수치

    private String object;

    @Enumerated(STRING)
    private ProgressStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void delete() {
        this.status = ProgressStatus.DELETED;
    }

    public void modify(Progress progress) {
        this.figure = progress.getFigure();
        this.object = progress.getObject();
    }

    public void changeStatus(ProgressStatus status) {
        this.status = status;
    }

    public static Progress of(int figure ,String object, Member member){
        return Progress.builder()
                .registDate(Instant.now())
                .figure(figure)
                .progress(0)
                .object(object)
                .member(member)
                .status(ProgressStatus.OPENED)
                .build();
    }

    public static Progress of(CreateProgressRequest request, Member member) {
        return of(request.getGoalFigure(), request.getSubject(), member);
    }

    public void stackFigure(int figure) {
        this.figure += Math.min(0, figure);
    }
}
