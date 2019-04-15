package com.lee.dao.mysql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 16:57 2018/8/21
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class UserPO implements Serializable {
    private static final long serialVersionUID = 44660234184450487L;

    @Id
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
