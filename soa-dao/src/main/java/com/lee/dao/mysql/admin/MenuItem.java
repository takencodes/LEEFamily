package com.lee.dao.mysql.admin;


import javax.persistence.*;
import java.util.List;

/**
 * 菜单
 */
@SuppressWarnings("serial") @Entity @Table(name = "adm_menuitem") public class MenuItem
    implements java.io.Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @Column(nullable = true) private Integer code;

    @Column(length = 50, nullable = false) private String name;

    @Column(length = 200) private String link;

    @Column(length = 255) private String description;

    @Column(length = 255) private String symbol;

    @ManyToOne private MainMenu mainMenu;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menuItem")
    private List<Resource> resources;

    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "adm_role_menuitem",
        joinColumns = {@JoinColumn(name = "menuitem_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"menuitem_id", "role_id"})})
    private List<Role> roles;

    public MenuItem() {

    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Long getId() {
        return id;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void copy(MenuItem item) {
        this.code = item.code;
        this.name = item.name;
        this.link = item.link;
        this.description = item.description;
        this.symbol = item.symbol;
    }

}
