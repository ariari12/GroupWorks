package kr.co.groupworks.materialflow.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ItemStatus {
    RECEIVING("입하예정"),
    STOCK_ENTRY("입고"),
    SHIPPING("출하예정"),
    ISSUING("출고"),
    NULL(null)
    ;
    private final String value;
    private static final Map<String, ItemStatus> VALUE_MAP = new HashMap<>();

    static {
        // Enum의 값과 매핑을 미리 생성하여 저장
        for (ItemStatus status : ItemStatus.values()) {
            VALUE_MAP.put(status.value, status);
        }
    }

    ItemStatus(String value) {
        this.value = value;
    }

    public static ItemStatus getItemStatus(Integer i) {
        if (i < 0 || i >= ItemStatus.values().length) {
            throw new IllegalArgumentException("Invalid index: " + i);
        }
        return ItemStatus.values()[i];
    }

    public static ItemStatus getItemStatus(String s) {
        return Optional.ofNullable(VALUE_MAP.get(s)).orElseThrow(() ->
                new IllegalArgumentException("No enum constant with value: " + s));
    }

    public static boolean checkedItemStatus(Integer i) {
        if (i < 0 || i >= ItemStatus.values().length) {
            throw new IllegalArgumentException("Invalid index: " + i);
        }
        return ItemStatus.getItemStatus(i) == ItemStatus.RECEIVING || ItemStatus.getItemStatus(i) == ItemStatus.SHIPPING;
    }
}
