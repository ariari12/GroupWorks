package kr.co.groupworks.materialflow.entity;

import jakarta.persistence.AttributeConverter;

public class OrderClassificationConverter implements AttributeConverter<OrderClassification, String> {

    @Override
    public String convertToDatabaseColumn(OrderClassification o) {
        return o == null ? null : o.getLabel();
    }

    @Override
    public OrderClassification convertToEntityAttribute(String s) {
        for (OrderClassification classification : OrderClassification.values()) {
            if (classification.getLabel().equals(s)) {
                return classification;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + s);
    }
}
