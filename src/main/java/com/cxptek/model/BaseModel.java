package com.cxptek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
