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
        return OrderClassification.values()[i];
    }
}
