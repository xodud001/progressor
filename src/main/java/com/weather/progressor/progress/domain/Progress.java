package com.weather.progressor.progress.domain;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long id;

    private Instant registDate;

    private int goal; // 설정한 목표 수치

    private int progress; // 진척도 현재 수치

    private String title;
}
