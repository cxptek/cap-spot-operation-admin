package com.cxptek.mapper;

import com.cxptek.api.serverconfig.request.ServerConfigRequest;
import com.cxptek.api.serverconfig.response.ServerConfigResponse;
import com.cxptek.entity.ServerConfig;
import com.cxptek.model.serverconfig.ServerConfigModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerConfigMapper {
    ServerConfigModel toServerConfigModel(ServerConfig entity);

    ServerConfigResponse toServerConfigResponse(ServerConfigModel model);

    ServerConfig toEntity(ServerConfigRequest request);

}
