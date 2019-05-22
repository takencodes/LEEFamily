package com.lee.dao.mysql.admin;

import java.util.List;

/**
 * 菜单
 */
public class Menu implements java.io.Serializable {

    private String name;
    private String url;

    private List<Menu> children;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Menu(String name, String url, List<Menu> children) {
        this.name = name;
        this.url = url;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}
