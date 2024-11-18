package com.cxptek.entity;

import com.cxptek.enums.SymbolType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SymbolTypeConverter implements AttributeConverter<SymbolType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SymbolType symbolType) {
        if (symbolType == null) {
            return null;
        }
        return symbolType.getCode();
    }

    @Override
    public SymbolType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return SymbolType.of(code);
    }
}
