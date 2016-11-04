package com.alpha.web.model;

import com.alpha.core.ws.utils.enums.EnvType;
import com.alpha.core.ws.utils.enums.ProtocolType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by jzhou237 on 2016-11-01.
 */
public class UploadInfoVo {

    @NotNull(message = "Service is null.")
    private String service;

    @NotNull(message = "File is null.")
    private MultipartFile file;

    private boolean isActive = true;

    private String aliasName;

    private String protocol;

    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public ProtocolType getProtocolType() {
        return ProtocolType.valueOf(this.protocol);
    }

    public EnvType getEnvType() {
        return EnvType.getEnvType(this.environment);
    }
}
