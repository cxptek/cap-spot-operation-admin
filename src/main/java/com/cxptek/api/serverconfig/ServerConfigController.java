package com.cxptek.api.serverconfig;

import com.cxptek.api.serverconfig.response.ServerConfigResponse;
import com.cxptek.constant.ApiPath;
import com.cxptek.exception.BusinessLogicException;
import com.cxptek.mapper.ServerConfigMapper;
import com.cxptek.service.ServerConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.SERVER_CONFIG)
@RequiredArgsConstructor
@Tag(name = "Token Controller", description = "Providing APIs helps the admin manage token information")
public class ServerConfigController {
    private final ServerConfigService serverConfigService;
    private final ServerConfigMapper serverConfigMapper;

    @GetMapping
    public List<ServerConfigResponse> getAssets() throws BusinessLogicException {
        return serverConfigService.getSymbolsConfigs().stream().map(serverConfigMapper::toServerConfigResponse).toList();
    }
}
