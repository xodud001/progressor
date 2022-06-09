package com.weather.progressor.app.progress.domain;

public enum ProgressStatus {

    OPENED, // 생성되었고 진척도가 진행되는 상태
    CLOSED, // 완료되진 않았지만 닫아놓은 상태, 다시 열 수 있음
    COMPLETED, // 진척도를 목표치까지 채운 상태
    DELETED // 삭제한 상태, 다시 복구 불가능
    ;
}
