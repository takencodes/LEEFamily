package com.lee.dao.mysql.vo.admin;

import java.util.Set;

/**
 * Created by death on 2016/7/25.
 */
public class AdminEditVo {

    public static final boolean ENABLED = true;
    public static final boolean DISABLED = false;
    /**
     * 账号
     */
    private String username;

    /**
     *
     */
    private boolean enabled = ENABLED;


    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     *
     */
    private String permitIP;

    /**
     * MSN
     */
    private String msn;

    /**
     * qq
     */
    private String qq;


    /**
     * 用户省公司
     */
    private Set<Long> companies;

    private String inviterCode;

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public static boolean isENABLED() {
        return ENABLED;
    }

    public static boolean isDISABLED() {
        return DISABLED;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPermitIP() {
        return permitIP;
    }

    public void setPermitIP(String permitIP) {
        this.permitIP = permitIP;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public Set<Long> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Long> companies) {
        this.companies = companies;
    }


}
