package com.weather.progressor.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

}