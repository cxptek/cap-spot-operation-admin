package com.cxptek.service;

import com.cxptek.api.serverconfig.request.ServerConfigRequest;
import com.cxptek.mapper.ServerConfigMapper;
import com.cxptek.model.serverconfig.ServerConfigModel;
import com.cxptek.repository.ServerConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerConfigServiceImpl implements ServerConfigService {
    private final ServerConfigRepository serverConfigRepository;
    private final ServerConfigMapper serverConfigMapper;

    @Override
    public ServerConfigModel createServerConfig(ServerConfigRequest request) {
        var serverConfig = serverConfigRepository.save(serverConfigMapper.toEntity(request));
        return serverConfigMapper.toServerConfigModel(serverConfig);
    }

    @Override
    public List<ServerConfigModel> getSymbolsConfigs() {
        return serverConfigRepository.findAll().stream()
                .map(serverConfigMapper::toServerConfigModel).toList();
    }
}
