package com.lee.dao.mysql.admin;

import javax.persistence.*;
import java.util.List;

/**
 * 菜单明细
 */
@SuppressWarnings("serial") @Entity @Table(name = "adm_main_menu") public class MainMenu
    implements java.io.Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @Column private Integer code;

    @Column(length = 50) private String name;

    @Column(length = 255) private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mainMenu")
    @OrderBy(value = "code") private List<MenuItem> menuItems;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_menugroup_menu",
        joinColumns = {@JoinColumn(name = "menugroup_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"menugroup_id", "menu_id"})})
    private List<MenuGroup> menuGroups;

    public MainMenu() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Integer getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    /**
     * {@linkplain MainMenu#menuGroups}
     */
    public List<MenuGroup> getMenuGroups() {
        return menuGroups;
    }

    /**
     * {@linkplain MainMenu#menuGroups}
     */
    public void setMenuGroups(List<MenuGroup> menuGroups) {
        this.menuGroups = menuGroups;
    }
}
