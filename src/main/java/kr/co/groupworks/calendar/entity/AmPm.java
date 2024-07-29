package kr.co.groupworks.calendar.entity;

import lombok.Getter;

@Getter
public enum AmPm {
    AM("오전",0.5),
    PM("오후",0.5);

    private final String name;
    private final double halfDay;

    AmPm(String name, double halfDay) {
        this.name = name;
        this.halfDay = halfDay;
    }
}
