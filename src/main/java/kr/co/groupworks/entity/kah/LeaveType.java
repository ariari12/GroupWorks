package kr.co.groupworks.entity.kah;

import lombok.Getter;

@Getter
public enum LeaveType {
    // 연차
    ANNUAL("연차"),
    // 병가
    SICK("병가"),
    // 기타
    OTHER("기타"),
    // 반차
    HALF("반차");

    private String leave;

    LeaveType(String leave) {
        this.leave = leave;
    }
}
