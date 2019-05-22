package com.lee.dao.mysql.admin;


import com.lee.dao.mysql.NameAndValueAndDescriptionAbleEnum;
import com.lee.dao.mysql.NameAndValueAndDescriptionAbleEnumConverter;

/**
 * 后台账户级别
 *
 * @author: liubin
 * @date 8/18/16 12:05
 */
public enum AdminLevel implements NameAndValueAndDescriptionAbleEnum {

    ADMIN_PLATFORM("平台用户", 1),
    ADMIN_COMPANY("省公司用户", 2),
    ADMIN_DEPOT("门店用户", 3);

    private String name;

    private Integer value;

    AdminLevel(String name, Integer value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return name;
    }

    public static class Converter
            extends NameAndValueAndDescriptionAbleEnumConverter<AdminLevel> {
    }

}
