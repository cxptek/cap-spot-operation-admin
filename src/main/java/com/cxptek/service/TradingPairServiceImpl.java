package com.cxptek.service;

import com.cxptek.entity.QTradingPair;
import com.cxptek.entity.TradingPair;
import com.cxptek.exception.BusinessLogicException;
import com.cxptek.exception.DataNotFoundException;
import com.cxptek.mapper.TradingPairMapper;
import com.cxptek.model.error.CommonErrorCode;
import com.cxptek.model.tradingpair.TradingPairModel;
import com.cxptek.repository.TradingPairRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradingPairServiceImpl implements TradingPairService {
    private final TradingPairMapper tradingPairMapper;

    private final TradingPairRepository tradingPairRepository;

    @Override
    public List<TradingPair> getTradingPairsIncludeInActive(String partnerId) throws BusinessLogicException {
        var conditions = QTradingPair.tradingPair.partnerId.eq(partnerId);
        return tradingPairRepository.fetch(conditions);
    }

    @Override
    public TradingPair getTradingPair(String symbolCode, String partnerId) throws BusinessLogicException {
        var conditions = QTradingPair.tradingPair.partnerId.eq(partnerId);
        conditions = conditions.and(QTradingPair.tradingPair.symbolCode.eq(symbolCode));
        var tradingPairs = tradingPairRepository.fetch(conditions);
        if (tradingPairs != null && !tradingPairs.isEmpty()) {
            return tradingPairs.getFirst();
        }
        throw new DataNotFoundException(CommonErrorCode.DATA_NOT_FOUND, "Not found trading pair");
    }

    @Override
    public List<TradingPairModel> getTradingPairByPartner(String partnerId) throws BusinessLogicException {
        var conditions = QTradingPair.tradingPair.partnerId.eq(partnerId);
        return tradingPairRepository.fetch(conditions).stream().map(tradingPairMapper::toTradingPairModel).toList();
    }

    @Override
    public TradingPairModel getTradingPairActiveBySymbolCode(String symbolCode) throws BusinessLogicException {
        var tradingPair = tradingPairRepository.findBySymbolCodeAndActiveTrue(symbolCode)
                .orElseThrow(() -> new DataNotFoundException(format("Cant find trading pair with symbol %s", symbolCode)));;
        return tradingPairMapper.toTradingPairModel(tradingPair);
    }
}
