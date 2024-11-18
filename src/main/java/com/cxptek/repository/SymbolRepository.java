package com.cxptek.repository;


import com.cxptek.entity.QSymbol;
import com.cxptek.entity.Symbol;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymbolRepository extends BaseRepository<Symbol, Long> {

    boolean existsTokenByCode(String code);
}
