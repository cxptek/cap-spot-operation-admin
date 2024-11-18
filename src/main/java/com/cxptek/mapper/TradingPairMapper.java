package com.cxptek.mapper;

import com.cxptek.api.tradingpair.response.TradingPairResponse;
import com.cxptek.entity.TradingPair;
import com.cxptek.model.tradingpair.TradingPairModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TradingPairMapper {
    TradingPairModel toTradingPairModel(TradingPair entity);

    TradingPairResponse toTradingPairResponse(TradingPairModel model);
}
