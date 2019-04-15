package com.lee.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Log配置类
 * Created by liqi1 on 2017/7/6.
 */
@Component
public class LogConfig {

    private Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.class);

    /**
     * 排除Url地址
     * 多个Url以[;]分开
     */
    private String excludedUri = "/user/login.do";

    /**
     * 是否启用
     */
    private boolean manualEnable = true;


    public String getExcludedUri() {
        return excludedUri;
    }

    public void setExcludedUri(String excludedUri) {
        this.excludedUri = excludedUri;
    }

    public boolean isManualEnable() {
        return manualEnable;
    }

    public void setManualEnable(boolean manualEnable) {
        this.manualEnable = manualEnable;
    }
}
