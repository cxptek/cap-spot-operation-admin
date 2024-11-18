package com.cxptek.api.serverconfig.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerConfigResponse implements Serializable {
    private String symbolCode;
    private String serverName;
}
