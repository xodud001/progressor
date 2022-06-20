package com.weather.progressor.app.progress.domain;


import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.progress.dto.CreateProgressRequest;
import com.weather.progressor.app.progress.dto.ModifyProgressForm;
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

    private String subject;

    @Enumerated(STRING)
    private ProgressStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Progress of(ModifyProgressForm form) {
        return builder()
                .goal(form.getGoal())
                .subject(form.getSubject())
                .build();
    }

    public void delete() {
        this.status = ProgressStatus.DELETED;
    }

    public void modify(Progress progress) {
        this.goal = progress.getGoal();
        this.subject = progress.getSubject();
    }

    public void changeStatus(ProgressStatus status) {
        this.status = status;
    }

    public static Progress of(int goal ,String subject, Member member){
        return Progress.builder()
                .registDate(Instant.now())
                .goal(goal)
                .progress(0)
                .subject(subject)
                .member(member)
                .status(ProgressStatus.OPENED)
                .build();
    }

    public static Progress of(CreateProgressRequest request, Member member) {
        return of(request.getGoalFigure(), request.getSubject(), member);
    }

    public void stackProgress(int progress) {
        this.progress += Math.max(0, progress);
    }

    public void toggleClose() {
        if(this.status == ProgressStatus.CLOSED){
            this.status = ProgressStatus.OPENED;
        }else{
            this.status = ProgressStatus.CLOSED;
        }
    }
}
