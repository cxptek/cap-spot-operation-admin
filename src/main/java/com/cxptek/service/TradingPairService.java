package com.cxptek.service;

import com.cxptek.entity.TradingPair;
import com.cxptek.exception.BusinessLogicException;
import com.cxptek.model.tradingpair.TradingPairModel;

import java.util.List;

public interface TradingPairService {
    List<TradingPair> getTradingPairsIncludeInActive(String partnerId) throws BusinessLogicException;

    TradingPair getTradingPair(String symbolCode, String partnerId) throws BusinessLogicException;

    List<TradingPairModel> getTradingPairByPartner(String partnerId) throws BusinessLogicException;

    TradingPairModel getTradingPairActiveBySymbolCode(String symbolCode) throws BusinessLogicException;
}
