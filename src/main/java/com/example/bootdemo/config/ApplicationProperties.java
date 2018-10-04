package com.example.bootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${app.context.path}")
    private String contextPath;

    @Value("${app.token.expiryTime}")
    private Long tokenExpiryTime;

    @Value("${app.resources.path}")
    private String resourcePath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Long getTokenExpiryTime() {
        return tokenExpiryTime;
    }

    public void setTokenExpiryTime(Long tokenExpiryTime) {
        this.tokenExpiryTime = tokenExpiryTime;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
