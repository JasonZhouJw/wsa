package com.alpha.web.services.model;

import com.alpha.core.common.utils.enums.EnvType;
import com.alpha.core.common.utils.enums.ProtocolType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2016-11-01.
 */
@Getter
@Setter
@ToString
public class UploadInfoVo {

    @NotNull(message = "Service is null.")
    private String service;

    @NotNull(message = "File is null.")
    private MultipartFile file;

    private boolean isActive = true;

    private String aliasName;

    private String protocol;

    private String environment;


    public ProtocolType getProtocolType() {
        return ProtocolType.valueOf(this.protocol);
    }

    public EnvType getEnvType() {
        return EnvType.getEnvType(this.environment);
    }
}
