package com.cxptek.repository;


import com.cxptek.entity.QServerConfig;
import com.cxptek.entity.ServerConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerConfigRepository extends BaseRepository<ServerConfig, Long> {
    QServerConfig qServerConfig = QServerConfig.serverConfig;

}
