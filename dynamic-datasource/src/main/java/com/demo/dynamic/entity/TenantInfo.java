package com.demo.dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantInfo {
    private Long id;

    private String kp;

    private String tenantId;

    private String tenantName;

    private String tenantDesc;

    private String createSource;

    private Long gmtCreate;

    private Long gmtModified;

}