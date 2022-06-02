package com.weather.progressor.progress.exception;

public class ProgressNotFountException extends RuntimeException{

    public ProgressNotFountException(Long id) {
        super(String.format("ID가 [%d]인 진척도를 찾지 못했습니다.", id));
    }

}
