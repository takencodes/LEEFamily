package com.lee.dao.mysql.admin;

import org.springframework.security.core.GrantedAuthority;

/**
 * 管理权限
 */
@SuppressWarnings("serial") public class AdminAuthority implements GrantedAuthority, Comparable {

    private String auth = null;

    public AdminAuthority(String auth) {
        super();
        this.auth = auth;
    }

    public int compareTo(Object o) {
        AdminAuthority oAuth = (AdminAuthority) o;
        if (oAuth == null || oAuth.getAuthority() == null)
            return -1;
        return oAuth.getAuthority().compareTo(this.auth);
    }

    public int hashCode() {
        return auth.hashCode();
    }

    public boolean equals(Object o) {
        AdminAuthority oAuth = (AdminAuthority) o;
        if (oAuth == null || oAuth.getAuthority() == null)
            return false;
        return oAuth.getAuthority().equals(this.auth);
    }

    public String getAuthority() {
        return auth;
    }

}
