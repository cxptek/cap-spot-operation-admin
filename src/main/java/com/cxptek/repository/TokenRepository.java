package com.cxptek.repository;

import com.cxptek.entity.QToken;
import com.cxptek.entity.Token;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends BaseRepository<Token, Long> {

    QToken qToken = QToken.token;
    boolean existsTokenByCode(String code);
    static BooleanExpression isTokenCode(String tokenCode) {
        return qToken.code.eq(tokenCode);
    }
    static BooleanExpression isTokenId(Long id) {
        return qToken.id.eq(id);
    }
}
