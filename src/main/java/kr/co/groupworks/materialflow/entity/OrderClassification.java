package kr.co.groupworks.materialflow.entity;

import lombok.Getter;

@Getter
public enum OrderClassification {
    SEND("발주"),
    RECEIVE("수주")
    ;
    private final String label;

    OrderClassification(String l) {
        this.label = l;
    }

    public static OrderClassification getClassification(Integer i) {
        if (i < 0 || i >= OrderClassification.values().length) {
            throw new IllegalArgumentException("Invalid index: " + i);
        }
        return OrderClassification.values()[i];
    }
}
