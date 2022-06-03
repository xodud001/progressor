package com.weather.progressor.progress.service;

import com.weather.progressor.MockitoBasedFrame;
import com.weather.progressor.progress.repository.ProgressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ProgressServiceTest extends MockitoBasedFrame {

    @Mock
    ProgressRepository progressRepository;

    @InjectMocks
    ProgressService progressService;

    @DisplayName("")
    @Test
    void test1(){

    }

}