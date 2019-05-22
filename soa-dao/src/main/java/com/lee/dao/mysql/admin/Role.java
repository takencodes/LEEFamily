package com.lee.dao.mysql.admin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *后台登录角色
 */
@SuppressWarnings("serial") @Entity @Table(name = "adm_role") public class Role
    implements java.io.Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @Column(length = 50, nullable = false) private String name;

    @Column(length = 255) private String description;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_role_menuitem",
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "menuitem_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "menuitem_id"})})
    private List<MenuItem> menuItems;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_role_resource",
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "resource_id"})})
    private List<Resource> resources;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItems == null) {
            menuItems = new ArrayList<MenuItem>();
        }
        if (!menuItems.contains(menuItem)) {
            menuItems.add(menuItem);
        }
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

    public Long getId() {
        return id;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return getName();
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }



}
