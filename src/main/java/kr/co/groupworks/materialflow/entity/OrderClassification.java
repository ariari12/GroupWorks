package kr.co.groupworks.materialflow.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

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

    public static OrderClassification getClassification(String label) {
        return Arrays.stream(OrderClassification.values())
                .filter(c -> Objects.equals(c.getLabel(), label)).findFirst()
                .orElse(null);
    }
}
