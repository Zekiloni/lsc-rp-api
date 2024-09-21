package net.lscrp.ucp.property;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BizTypeConverter implements AttributeConverter<PropertyEntity.BizType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PropertyEntity.BizType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.ordinal();
    }

    @Override
    public PropertyEntity.BizType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return PropertyEntity.BizType.values()[dbData];
    }
}
