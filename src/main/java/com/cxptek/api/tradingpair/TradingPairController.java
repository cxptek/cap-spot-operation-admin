package com.cxptek.api.tradingpair;

import com.cxptek.api.tradingpair.response.TradingPairResponse;
import com.cxptek.constant.ApiPath;
import com.cxptek.mapper.TradingPairMapper;
import com.cxptek.service.TradingPairService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiPath.TRADING_PAIR)
@RequiredArgsConstructor
@Tag(name = "Trading Pair Controller", description = "Providing APIs helps the admin manage token information")
public class TradingPairController {
    private final TradingPairService tradingPairService;
    private final TradingPairMapper tradingPairMapper;

    @GetMapping("/{partnerId}")
    public List<TradingPairResponse> getTradingPair(@PathVariable String partnerId) {
        return tradingPairService.getTradingPairByPartner(partnerId.toString()).stream()
                .map(tradingPairMapper::toTradingPairResponse).toList();
    }

    @GetMapping("/symbol/{symbolCode}")
    public TradingPairResponse getTradingPairBySymbolCode(@PathVariable String symbolCode) {
        var tradingPair = tradingPairService.getTradingPairActiveBySymbolCode(symbolCode);
        return tradingPairMapper.toTradingPairResponse(tradingPair);
    }
}
