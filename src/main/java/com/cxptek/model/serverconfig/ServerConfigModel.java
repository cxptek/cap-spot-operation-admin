package com.cxptek.model.serverconfig;

import com.cxptek.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerConfigModel extends BaseModel {
    private Long id;
    private String symbolCode;
    private String serverName;
}
