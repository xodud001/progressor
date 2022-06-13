package com.weather.progressor.app.progress.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.weather.progressor.app.progress.domain.ProgressStatus.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Progress")
class ProgressTest {

    @DisplayName("1. delete()")
    @Test
    void test1(){
        Progress progress = Progress.builder()
                .status(OPENED)
                .build();

        progress.delete();

        assertThat(progress.getStatus()).isEqualTo(DELETED);
    }

    @DisplayName("2. modify()")
    @Test
    void test2(){
        Progress progress = Progress.builder()
                .goal(100)
                .title("테스트용 목표")
                .build();

        Progress request = Progress.builder()
                .goal(200)
                .title("업데이트용")
                .build();

        progress.modify(request);

        assertThat(progress.getFigure()).isEqualTo(request.getFigure());
        assertThat(progress.getObject()).isEqualTo(request.getObject());
    }

    @DisplayName("3. changeStatus() - Normal")
    @Test
    void test3(){
        Progress progress = Progress.builder()
                .status(OPENED)
                .build();

        progress.changeStatus(CLOSED);

        assertThat(progress.getStatus()).isEqualTo(CLOSED);
    }

}