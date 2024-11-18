package com.cxptek.service;

import com.cxptek.api.serverconfig.request.ServerConfigRequest;
import com.cxptek.model.serverconfig.ServerConfigModel;

import java.util.List;

public interface ServerConfigService {
    ServerConfigModel createServerConfig(ServerConfigRequest request);
    List<ServerConfigModel> getSymbolsConfigs();
}
