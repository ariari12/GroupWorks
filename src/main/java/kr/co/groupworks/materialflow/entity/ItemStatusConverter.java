package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.AttributeConverter;

public class ItemStatusConverter implements AttributeConverter<ItemStatus, String> {
    @Override
    public String convertToDatabaseColumn(ItemStatus i) {
        return i == null ? "" : i.getValue();
    }

    @Override
    public ItemStatus convertToEntityAttribute(String s) {
        return ItemStatus.valueOf(s);
    }
}
