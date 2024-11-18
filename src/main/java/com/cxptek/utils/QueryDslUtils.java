package com.cxptek.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.Data;

@Data
public class QueryDslUtils {
    public static BooleanExpression alwaysTrue() {
        return Expressions.asBoolean(Boolean.TRUE).isTrue();
    }
}
