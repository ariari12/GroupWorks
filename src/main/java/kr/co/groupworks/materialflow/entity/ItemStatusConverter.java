package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.AttributeConverter;

public class ItemStatusConverter implements AttributeConverter<ItemStatus, String> {
    @Override
    public String convertToDatabaseColumn(ItemStatus i) {
        return i == null ? null : i.getValue();
    }

    @Override
    public ItemStatus convertToEntityAttribute(String s) {
        if(s==null) return null;
        for (ItemStatus itemStatus : ItemStatus.values()) {
            if (itemStatus.getValue().equals(s)) {
                return itemStatus;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + s);
    }
}
