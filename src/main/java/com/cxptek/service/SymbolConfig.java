package com.cxptek.service;

import com.cxptek.model.symbol.SymbolModel;

import java.util.List;

public class SymbolConfig {
    private List<SymbolModel> symbol;

    public SymbolConfig(List<SymbolModel> symbol) {
        this.symbol = symbol;
    }

    public List<SymbolModel> getSymbol() {
        return symbol;
    }

    public void setSymbol(List<SymbolModel> symbol) {
        this.symbol = symbol;
    }
}


