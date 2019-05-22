package com.lee.dao.mysql.vo.admin;

import java.util.List;

/**
 * Created by defei on 9/12/16.
 */
public class MenuGroupReqVo {

    private Long id;

    private String name;

    private String description;

    private Integer idx;

    private List<Long> mainMenuIds;

    /**
     * {@linkplain MenuGroupReqVo#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * {@linkplain MenuGroupReqVo#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@linkplain MenuGroupReqVo#name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain MenuGroupReqVo#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@linkplain MenuGroupReqVo#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@linkplain MenuGroupReqVo#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@linkplain MenuGroupReqVo#idx}
     */
    public Integer getIdx() {
        return idx;
    }

    /**
     * {@linkplain MenuGroupReqVo#idx}
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    /**
     * {@linkplain MenuGroupReqVo#mainMenuIds}
     */
    public List<Long> getMainMenuIds() {
        return mainMenuIds;
    }

    /**
     * {@linkplain MenuGroupReqVo#mainMenuIds}
     */
    public void setMainMenuIds(List<Long> mainMenuIds) {
        this.mainMenuIds = mainMenuIds;
    }
}
