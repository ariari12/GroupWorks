package kr.co.groupworks.materialflow.entity;

import lombok.Getter;

@Getter
public enum OrderClassification {
    SEND("발주"),
    RECEIVE("수주")
    ;
    private final String label;

    OrderClassification(String label) {
        this.label = label;
    }
}
