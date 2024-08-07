package kr.co.groupworks.materialflow.entity;

import lombok.Getter;

@Getter
public enum ItemStatus {
    RECEIVING("입하예정"),
    STOCK_ENTRY("입고"),
    PRODUCTION("생산"),
    SHIPPING("출하예정"),
    ISSUING("출고")
    ;
    private final String value;

    ItemStatus(String value) {
        this.value = value;
    }
}
