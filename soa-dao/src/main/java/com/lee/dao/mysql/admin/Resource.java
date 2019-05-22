package com.lee.dao.mysql.admin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 后台 资源
 */
@SuppressWarnings("serial") @Entity @Table(name = "adm_resource") public class Resource
    implements Serializable {

    public final static int TYPE_MENU = 0;
    public final static int TYPE_URL = 1;

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @Column(length = 50, nullable = false) private String name;

    @Column(length = 255) private String symbol;

    @Column(length = 255) private String description;

    @ManyToOne private MenuItem menuItem;


    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_role_resource",
        joinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"resource_id", "role_id"})})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void copy(Resource res) {
        this.name = res.name;
        this.description = res.description;
        this.symbol = res.symbol;
    }
}
