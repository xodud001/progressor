package com.weather.progressor.app.progressdetail.domain;


import com.weather.progressor.app.progress.domain.Progress;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "progress_detail")
public class ProgressDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    private Instant recordAt; // 이벤트가 기록된 시점

    private int progressSlice; // 해당 이벤트로 추가되는 진척도 수치

    @ManyToOne
    @JoinColumn(name = "progress_id")
    private Progress progress;

    public static ProgressDetail of(int progressSlice, Progress progress) {
        return ProgressDetail.builder()
                .recordAt(Instant.now())
                .progressSlice(progressSlice)
                .progress(progress)
                .build();
    }
}
