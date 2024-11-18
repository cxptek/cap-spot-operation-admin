package com.cxptek.repository;

import com.cxptek.entity.QTradingPair;
import com.cxptek.entity.TradingPair;

import java.util.Optional;

public interface TradingPairRepository extends BaseRepository<TradingPair, Long> {

    QTradingPair qTradingPair = QTradingPair.tradingPair;
    Optional<TradingPair> findBySymbolCodeAndActiveTrue(String symbolCode);
}
