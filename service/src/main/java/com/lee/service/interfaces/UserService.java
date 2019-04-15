package com.lee.service.interfaces;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 11:23 2018/8/16
 */
public interface UserService {

    /**
     * 登陸
     * @param username
     * @param password
     */
    void login(String username, String password);

    void init(String password);

}
